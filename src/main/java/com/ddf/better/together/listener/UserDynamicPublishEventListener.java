package com.ddf.better.together.listener;

import cn.hutool.core.collection.CollectionUtil;
import com.ddf.better.together.constants.enumeration.UserDynamicViewLevelEnum;
import com.ddf.better.together.event.UserPublishDynamicEvent;
import com.ddf.better.together.model.dto.UserPublishDynamicEventDTO;
import com.ddf.better.together.model.entity.UserDynamicReceiveBox;
import com.ddf.better.together.model.entity.UserPartner;
import com.ddf.better.together.service.IUserDynamicReceiveBoxService;
import com.ddf.better.together.service.IUserPartnerService;
import com.ddf.boot.common.core.util.JsonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/06 22:58
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserDynamicPublishEventListener implements ApplicationListener<UserPublishDynamicEvent> {

    private final IUserPartnerService userPartnerService;

    private final IUserDynamicReceiveBoxService userDynamicReceiveBoxService;

    private final ThreadPoolTaskExecutor fillDynamicReceiveBoxPool;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(UserPublishDynamicEvent event) {
        fillDynamicReceiveBoxPool.execute(() -> {
            final UserPublishDynamicEventDTO dto = event.getDto();
            log.info("用户[{}]发布动态[{}]事件: {}", dto.getUid(), dto, JsonUtil.asString(event));
            doPartnerDynamicReceiveBox(dto);
        });
    }

    /**
     * 处理发布动态的用户的好友的动态收件箱
     * 目前采用推的方式，将动态推送到当前用户的好友里
     */
    private void doPartnerDynamicReceiveBox(UserPublishDynamicEventDTO dto) {
        List<UserDynamicReceiveBox> saveList = new ArrayList<>();
        // 给当前发布人自己也增加一条动态
        final UserDynamicReceiveBox box = new UserDynamicReceiveBox();
        box.setUid(dto.getUid());
        box.setDynamicId(dto.getUserDynamicId());
        box.setPublishTime(dto.getPublishTime());
        saveList.add(box);

        int batchSize = 1000;
        if (Objects.equals(UserDynamicViewLevelEnum.FRIEND.getCode(), dto.getViewLevel())) {
            final List<UserPartner> partners = userPartnerService.getUserPartners(dto.getUid());
            if (CollectionUtil.isEmpty(partners)) {
                userDynamicReceiveBoxService.saveBatch(saveList);
                return;
            }
            // 推模式， 遍历发布人所有的好友，然后增加一条动态消息
            for (UserPartner partner : partners) {
                final UserDynamicReceiveBox temp = new UserDynamicReceiveBox();
                temp.setUid(partner.getPartnerUid());
                temp.setDynamicId(dto.getUserDynamicId());
                temp.setPublishTime(dto.getPublishTime());
                saveList.add(temp);

                if (saveList.size() == batchSize) {
                    userDynamicReceiveBoxService.saveBatch(saveList);
                    saveList.clear();
                }
            }
        }
        if (CollectionUtil.isNotEmpty(saveList)) {
            userDynamicReceiveBoxService.saveBatch(saveList);
        }
    }
}

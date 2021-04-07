package com.ddf.better.together.listener;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddf.better.together.constants.BizRuleConst;
import com.ddf.better.together.constants.enumration.UserPartnerStatusEnum;
import com.ddf.better.together.event.UserPartnerStatusChangeEvent;
import com.ddf.better.together.model.dto.UserDynamicDTO;
import com.ddf.better.together.model.dto.UserPartnerStatusChangeDTO;
import com.ddf.better.together.model.entity.UserDynamicReceiveBox;
import com.ddf.better.together.model.request.SearchUserDynamicRequest;
import com.ddf.better.together.service.IUserDynamicReceiveBoxService;
import com.ddf.better.together.service.IUserDynamicService;
import com.ddf.boot.common.core.model.PageRequest;
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
 * <p>用户伙伴关系变更事件监听</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/07 23:04
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserPartnerStatusChangeEventListener implements ApplicationListener<UserPartnerStatusChangeEvent> {

    private final IUserDynamicService userDynamicService;

    private final ThreadPoolTaskExecutor fillDynamicReceiveBoxPool;

    private final IUserDynamicReceiveBoxService userDynamicReceiveBoxService;


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(UserPartnerStatusChangeEvent event) {
        fillDynamicReceiveBoxPool.execute(() -> {
            final UserPartnerStatusChangeDTO dto = event.getDto();
            log.info("发布用户[{}-{}]关系[{}]变更事件", dto.getUid(), dto.getPartnerUid(), dto.getTargetStatus());
            fillDynamicReceiveBox(dto);
        });
    }

    private void fillDynamicReceiveBox(UserPartnerStatusChangeDTO dto) {
        if (Objects.equals(UserPartnerStatusEnum.ACTIVE, dto.getTargetStatus())) {
            // 需要保存的动态收件箱的数据
            List<UserDynamicReceiveBox> saveList = new ArrayList<>();

            // 分别查询伙伴双方各自发送的仅好友可见的动态
            final SearchUserDynamicRequest request = new SearchUserDynamicRequest();
            request.setUid(dto.getUid());
            request.setPageNum(PageRequest.DEFAULT_PAGE_NUM);
            request.setPageSize(BizRuleConst.NEW_PARTNER_DYNAMIC_FETCH_NUM);
            final Page<UserDynamicDTO> page = userDynamicService.searchUserDynamic(request);
            if (CollectionUtil.isNotEmpty(page.getRecords())) {
                for (UserDynamicDTO record : page.getRecords()) {
                    final UserDynamicReceiveBox box = new UserDynamicReceiveBox();
                    box.setDynamicId(record.getId());
                    box.setUid(dto.getPartnerUid());
                    box.setPublishTime(record.getPublishTime());
                    saveList.add(box);
                }
            }
            log.info("增加用户[{}]收件箱中[{}]的动态数据{}条", dto.getPartnerUid(), dto.getUid(), page.getRecords().size());

            // 简单粗暴先查询两次
            request.setUid(dto.getPartnerUid());
            final Page<UserDynamicDTO> user2Page = userDynamicService.searchUserDynamic(request);
            if (CollectionUtil.isNotEmpty(user2Page.getRecords())) {
                for (UserDynamicDTO record : user2Page.getRecords()) {
                    final UserDynamicReceiveBox box = new UserDynamicReceiveBox();
                    box.setDynamicId(record.getId());
                    box.setUid(dto.getUid());
                    box.setPublishTime(record.getPublishTime());
                    saveList.add(box);
                }
            }
            log.info("增加用户[{}]收件箱中[{}]的动态数据{}条", dto.getUid(), dto.getPartnerUid(), user2Page.getRecords().size());
            userDynamicReceiveBoxService.saveBatch(saveList);
        } else if (Objects.equals(UserPartnerStatusEnum.CANCELED, dto.getTargetStatus())) {
            userDynamicReceiveBoxService.deletePartnerDynamic(dto.getUid());
            userDynamicReceiveBoxService.deletePartnerDynamic(dto.getPartnerUid());
        }
    }
}

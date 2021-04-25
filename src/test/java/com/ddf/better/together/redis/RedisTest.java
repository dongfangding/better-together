package com.ddf.better.together.redis;

import com.ddf.better.together.ApplicationTest;
import com.ddf.better.together.business.UserTaskDefinitionBizService;
import com.ddf.better.together.model.request.UserTaskDefinitionRequest;
import com.ddf.better.together.model.response.UserTaskDefinitionResponse;
import com.ddf.boot.common.core.model.PageResult;
import com.ddf.boot.common.core.model.UserClaim;
import com.ddf.boot.common.core.util.UserContextUtil;
import com.ddf.boot.common.redis.helper.GeoHelper;
import com.ddf.boot.common.redis.request.GeoCoordinateSearchRequest;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.redisson.api.GeoOrder;
import org.redisson.api.GeoPosition;
import org.redisson.api.GeoUnit;
import org.redisson.api.RGeo;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2021/04/25 11:43
 */
public class RedisTest extends ApplicationTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private GeoHelper geoHelper;

    @Autowired
    private UserTaskDefinitionBizService userTaskDefinitionBizService;

    @Autowired
    private RedisTemplate redistemplate;
    
    @Test
    public void testGeo() {
        final RGeo<String> geo = geoHelper.get("geo:test");
        String key = "geo:test";

        geoHelper.add(key, 13.361389, 38.115556, "Palermo");
        geoHelper.add(key, 15.087269, 37.502669, "Catalina");
        geoHelper.add(key, 14.087269, 37.502669, "ZhangSan");
        geoHelper.add(key, 15.087169, 38.502669, "LiSi");
        geoHelper.add(key, 18.087269, 37.502669, "WangEr");
        geoHelper.add(key, 25.087269, 39.502669, "MaZi");

        final Double dist = geoHelper.dist(key, "Palermo", "MaZi", GeoUnit.KILOMETERS);
        System.out.printf("相距距离: %s %s%n", dist, GeoUnit.KILOMETERS.name());

        final GeoCoordinateSearchRequest build = GeoCoordinateSearchRequest.builder()
                .key(key)
                .longitude(14.361389)
                .latitude(37.502669)
                .radius(200.0)
                .geoUnit(GeoUnit.KILOMETERS)
                .geoOrder(GeoOrder.ASC)
                .count(2)
                .build();

        final List<String> radius = geoHelper.radius(build);
        System.out.println("radius = " + radius);


        final Map<String, Double> map = geoHelper.radiusWithDistance(build);
        System.out.println("map = " + map);

        final Map<String, GeoPosition> map1 = geoHelper.radiusWithPosition(build);
        System.out.println("map1 = " + map1);
    }


    @Test
    public void testCodec() {
        UserContextUtil.setUserClaim(new UserClaim("1377909795931066461", "chen"));

        final UserTaskDefinitionRequest request = new UserTaskDefinitionRequest();
        request.setPageNum(1);
        request.setPageSiz(2);
        final PageResult<UserTaskDefinitionResponse> result = userTaskDefinitionBizService.myTaskDefinition(request);
        redissonClient.getList("haha").add(result);

        stringRedisTemplate.opsForList().leftPush("test_str_list", result.toString());

        redistemplate.opsForValue().set("test_template_list", result);
    }




}

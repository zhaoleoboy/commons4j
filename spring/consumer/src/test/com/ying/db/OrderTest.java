package com.ying.db;

import com.alibaba.fastjson.JSON;
import com.ying.model.OrderModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 字符串缓存
     */
    @Test
    public void testString() {
        LOGGER.info("测试字符串插入缓存！");
        stringRedisTemplate.opsForValue().set("name", "ying");
        Assert.assertEquals("ying", stringRedisTemplate.opsForValue().get("name"));
    }

    /**
     * 对象持久化为json，然后再缓存
     */
    @Test
    public void testOrder() {
        OrderModel model = new OrderModel();
        model.setCreateTime(new Date());
        stringRedisTemplate.opsForValue().set("order", JSON.toJSONString(model));
        String order = stringRedisTemplate.opsForValue().get("order");
        OrderModel destModel = JSON.parseObject(order, OrderModel.class);
        System.out.println(destModel.getCreateTime());
    }
}

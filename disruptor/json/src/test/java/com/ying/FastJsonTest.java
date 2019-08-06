package com.ying;

import com.alibaba.fastjson.JSON;
import com.ying.model.JsonInfo;
import org.testng.annotations.Test;

public class FastJsonTest {

    /**
     * fastjson序列化时，属性的首字母被转换为小写！
     */
    @Test
    public void testBigAttr(){
        JsonInfo ying = new JsonInfo("ying");
        String jsonString = JSON.toJSONString(ying);
        System.out.println(jsonString);
    }
}

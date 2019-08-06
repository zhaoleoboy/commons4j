package com.ying;

import com.google.gson.Gson;
import com.ying.model.JsonInfo;
import org.testng.annotations.Test;

public class GsonTest {

    /**
     * 完美解决属性首字母大写的问题
     */
    @Test
    public void testBigAttr() {
        JsonInfo ying = new JsonInfo("ying");
        Gson gson = new Gson();
        String s = gson.toJson(ying);
        System.out.println(s);
    }
}

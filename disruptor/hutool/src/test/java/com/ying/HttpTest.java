package com.ying;

import cn.hutool.http.HttpUtil;
import org.testng.annotations.Test;

public class HttpTest {

    @Test
    public void testGet() {
        String url = "http://www.gszwfw.gov.cn/gszw/zxts/frontcontent.do?webid=1&areacode=620000000000&sysid=3" +
                "&type=2&_=1565080496050&pageno=";
        for (int i = 1; i < 2; i++) {
            String s = HttpUtil.get(url + i);
            System.out.println(s);
        }

    }
}

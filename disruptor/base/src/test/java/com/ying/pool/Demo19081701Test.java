package com.ying.pool;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo19081701Test {

    @Test()
    public void test() {
        new Demo190817().test();
    }

    @Test
    public void testWithExecutors() {
        new Demo190817().testWithExecutors();
    }

}

package com.ying.service.impl;

import java.util.Date;

import com.alibaba.dubbo.config.annotation.Service;
import com.ying.service.HelloService;

@Service(version = "${user.service.version}")
public class HelloServiceImpl implements HelloService {

	@Override
    public String sayHello(String name) {
        return "Hello, " + name + ", " + new Date();
    }

}
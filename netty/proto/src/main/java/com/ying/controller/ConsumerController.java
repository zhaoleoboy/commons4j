package com.ying.controller;

import com.ying.client.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ConsumerController {

    @Autowired
    private NettyClient client;

    @GetMapping("/send")
    @ResponseBody
    public String send(String message) {
        client.sendMsg(message);
        return "success";
    }
}

package com.ying.controller;

//import com.ying.channel.ShopChannel;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableBinding(ShopChannel.class)
public class ProducerController {
//    private MessageChannel sendShopMessageChannel;
//
//    @GetMapping("/sendMsg")
//    public String sendShopMessage(String content) {
//        boolean isSendSuccess = sendShopMessageChannel.
//                send(MessageBuilder.withPayload(content).build());
//        return isSendSuccess ? "发送成功" : "发送失败";
//    }
//
//    @StreamListener(ShopChannel.SHOP_INPUT)
//    public void receive(Message<String> message) {
//        System.out.println(message.getPayload());
//    }
}
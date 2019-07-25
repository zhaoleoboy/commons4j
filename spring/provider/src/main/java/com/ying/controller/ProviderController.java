package com.ying.controller;

import com.ying.model.OrderModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ProviderController {

    @GetMapping("/provider/{id}")
    public OrderModel findOrderById(@PathVariable Long id) {
        OrderModel model = new OrderModel();
        model.setId(id);
        model.setCreateTime(new Date());
        model.setPayTime(new Date());
        return model;
    }
}

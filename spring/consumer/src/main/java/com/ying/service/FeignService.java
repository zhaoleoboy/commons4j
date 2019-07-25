package com.ying.service;

import com.ying.model.OrderModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feign-consumer")
public interface FeignService {

    @GetMapping(value = "/cs/order/{id}")
    public OrderModel findOrderById(@PathVariable("id") Long id);
}

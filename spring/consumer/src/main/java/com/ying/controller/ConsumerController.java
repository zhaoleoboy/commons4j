package com.ying.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import com.ying.ConsumerApplication;
import com.ying.model.OrderModel;
import com.ying.service.FeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("cs")
public class ConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);

    @Autowired
    private DiscoveryClient discoveryClient;

//    @Autowired
//    private FeignService feignService;

    @Autowired
    private RestTemplate restTemplate;

    public String getServiceUrl() {

        List<ServiceInstance> providers = discoveryClient.getInstances("provider");
        if (providers != null && providers.size() > 0) {
            return String.valueOf(providers.get(0).getUri());
        }
        return null;
    }

//    @GetMapping("/findOrderByIdWithFeign/{id}")
//    public OrderModel findOrderByIdWithFeign(@PathVariable Long id){
//        return feignService.findOrderById(id);
//    }

    @GetMapping("/findOrderById/{id}")
    public OrderModel findOrderById(@PathVariable Long id) {
        String serviceUrl = getServiceUrl();
        serviceUrl = serviceUrl + "/order/" + id;
        System.out.println(String.format("provider地址为：%s", serviceUrl));
        return this.restTemplate.getForObject(serviceUrl, OrderModel.class);
    }
}

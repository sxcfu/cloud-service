package com.starfast.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2019/4/9.
 */
@RestController
public class RibbonController {

    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @GetMapping("/provide")
    public String getMsg() {
        List<String> services = client.getServices();

        if (!services.isEmpty() && services.size() > 0) {
            String serId = services.get(0);
            List<ServiceInstance> instances = client.getInstances(serId);

            if (!instances.isEmpty() && instances.size() > 0) {
                ServiceInstance instance = instances.get(0);
                logger.info(
                        "/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
            }
        }


        return "hello spring cloud";
    }
}

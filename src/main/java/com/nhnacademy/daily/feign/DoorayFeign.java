package com.nhnacademy.daily.feign;

import com.nhnacademy.daily.model.MessagePayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "doorayFeign", url = "https://hook.dooray.com/services")
public interface DoorayFeign {

    @PostMapping("/{serviceId}/{botId}/{botToken}")
    String sendMessage(@RequestBody MessagePayload messagePayload,
                       @PathVariable String serviceId, @PathVariable String botId, @PathVariable String botToken);

}

package com.nhnacademy.daily.service;

import com.nhnacademy.daily.feign.DoorayFeign;
import com.nhnacademy.daily.model.MessagePayload;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoorayService {

    private final DoorayFeign doorayFeign;

    public void sendMessage() {
        doorayFeign.sendMessage(new MessagePayload("Hello", "hi"), "3204376758577275363", "4045901689874472590", "W0RgKMoPTUO3RejIIJVQcg");
    }

}

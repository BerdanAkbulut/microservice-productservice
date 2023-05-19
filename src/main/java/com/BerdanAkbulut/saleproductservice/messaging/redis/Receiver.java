package com.BerdanAkbulut.saleproductservice.messaging.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {
    public void execute(String message) {
        log.debug("Received message, {}", message);
    }
}

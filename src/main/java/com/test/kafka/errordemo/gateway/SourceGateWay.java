package com.test.kafka.errordemo.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
    @MessagingGateway(defaultRequestChannel="start", errorChannel="errors")
    public interface SourceGateWay {

         String publishWPTMessage(String start);

    }





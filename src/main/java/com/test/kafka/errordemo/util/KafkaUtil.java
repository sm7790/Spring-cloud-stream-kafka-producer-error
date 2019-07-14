package com.test.kafka.errordemo.util;

import com.test.kafka.errordemo.config.MessageChannelBinder;
import com.test.kafka.errordemo.gateway.SourceGateWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class KafkaUtil  {

@Autowired
SourceGateWay gateWay;

    @Scheduled(cron="*/1 * * * * *")
    public void callDataGen() {
        gateWay.publishWPTMessage("start") ;
    }
}

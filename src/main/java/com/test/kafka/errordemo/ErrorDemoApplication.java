package com.test.kafka.errordemo;

import com.test.kafka.errordemo.config.MessageChannelBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@SpringBootApplication
@IntegrationComponentScan
public class ErrorDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorDemoApplication.class, args);
    }
}

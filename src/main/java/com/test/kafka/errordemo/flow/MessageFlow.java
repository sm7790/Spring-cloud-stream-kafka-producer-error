package com.test.kafka.errordemo.flow;


import com.test.kafka.errordemo.config.MessageChannelBinder;
import com.test.kafka.errordemo.util.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@EnableBinding(MessageChannelBinder.class)
public class MessageFlow {

    private MessageChannel out;

    @Autowired
    public MessageFlow(MessageChannelBinder mBinder) {
        this.out = mBinder.messageOut();
    }

    @Autowired
    MsgUtil util;



    @Bean
    public IntegrationFlow main() {
        return IntegrationFlows.from(MessageChannels.direct("start"))
                .enrichHeaders(s -> s.header(MessageHeaders.ERROR_CHANNEL, "errors", true))
                .handle("util", "msgGenerator")
                .channel(out)
                .get();
    }


//    @Bean
//    public IntegrationFlow pubsub2() { // second subscriber
//        return IntegrationFlows.from("pubsub")
//                .enrichHeaders(s -> s.header(MessageHeaders.ERROR_CHANNEL, "errors", true))
//                .handle("handlers", "third")
//                .handle("handlers", "fourth")
//                .get();
//    }

    @Bean
    public IntegrationFlow errorFlow() {
        return IntegrationFlows.from("errors")
                .handle("util", "errors")
                .get();
    }


    @Bean
    public Executor exec() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(10);
        return exec;
    }


    @Bean
    public MsgUtil util() {
        return util;
    }
}

package com.test.kafka.errordemo.config;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageChannelBinder {


 String MSG_OUT = "testMsgOut";

 String MSG_IN = "testMsgIn";

    @Output(MSG_OUT)
    MessageChannel messageOut();


    @Input(MSG_IN)
    KStream<String,String> messageIn();


}

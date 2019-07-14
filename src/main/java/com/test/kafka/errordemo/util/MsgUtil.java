package com.test.kafka.errordemo.util;


import com.test.kafka.errordemo.config.MessageChannelBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@EnableBinding(MessageChannelBinder.class)
public class MsgUtil {



    public Message msgGenerator(){
        String[] randomWords = new String[]{"foo", "bar", "foobar", "baz", "fox"};
        Random random = new Random();


            int idx = random.nextInt(5);

                String msg = randomWords[idx];

               return  sendMsgToOutputChannel(idx,msg);


    }


    private Message sendMsgToOutputChannel(int idx,String msg) {

       Message smsg = MessageBuilder.withPayload(msg).setHeader(KafkaHeaders.MESSAGE_KEY,String.valueOf(idx)).build();

        //throw new RuntimeException("testing exception with "+smsg);
      //  out.send(smsg);
      return smsg;
    }



//    @StreamListener("errors")
//    public void error(Message<?> message) {
//        System.out.println("Handling ERROR: " + message);
//    }




    public void errors(ErrorMessage in) {
        System.out.println(Thread.currentThread().getName() + " " + in);
        MessagingException exception = (MessagingException) in.getPayload();
        if (exception.getFailedMessage().getHeaders().getErrorChannel() != "errors") {
            throw new RuntimeException("Handled error for " + in);
        }
        else {
            System.out.println("Exception occurred on secondary async flow");
        }
    }

}

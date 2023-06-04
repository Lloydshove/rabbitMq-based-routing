package shovell.latencylogger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class KafkaConsumer {

    public KafkaConsumer(){
        System.out.println("Starting KafkaConsumer");
    }
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
    @KafkaListener(topics = "command-topic", groupId = "latency-group-id")
    public void listenRouterGroupForCommands(@Payload String message, @Headers() ConsumerRecord headers) {
        System.out.println("Command at " + dateFormat.format(new Date(headers.timestamp())));

    }
    @KafkaListener(topics = "event-topic", groupId = "latency-group-id")
    public void listenRouterGroupForEvents(@Payload String message, @Headers() ConsumerRecord headers) {
        System.out.println("Event at   " + dateFormat.format(new Date(headers.timestamp())) + "\n");

    }

    private static String shortMessage(String message) {
        return message.length() > 70 ? message.substring(0, 70) : message;
    }
}


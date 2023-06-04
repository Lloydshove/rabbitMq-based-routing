package shovell.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private RabbitMqProducer rabbitMqProducer;

    public KafkaConsumer(){
        System.out.println("Starting KafkaConsumer");
    }
    @KafkaListener(topics = "command-topic", groupId = "router-group-id")
    public void listenRouterGroup(String message) {
        System.out.println("Received Message over kafka: " +shortMessage(message));

        rabbitMqProducer.sendMessage(message);
        System.out.println("Sent Message to RabbitMq");
    }

    private static String shortMessage(String message) {
        return message.length() > 70 ? message.substring(0, 70) : message;
    }
}


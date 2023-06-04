package shovell.engine;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {

    @Autowired KafkaProducer kafkaProducer;

    @RabbitListener(queues = {"statelessQueue"})
    public void consume(String message){
        System.out.println("Message read from RabbitMq:" + shortMessage(message));

        kafkaProducer.sendMessage(message);

        System.out.println("Message Written To Topic: " + shortMessage(message));
    }

    private String shortMessage(String message) {
        return message.length() < 70 ? message : message.substring(0, 70);
    }

}
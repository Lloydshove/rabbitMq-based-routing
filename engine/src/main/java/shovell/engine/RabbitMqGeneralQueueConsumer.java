package shovell.engine;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqGeneralQueueConsumer {

    @Autowired KafkaProducer kafkaProducer;
    @Autowired MessageHelper helper;

    @RabbitListener(queues = {"statelessQueue"})
    public void consume(String message){
        System.out.println("Message read from RabbitMq:" + helper.shortMessage(message));

        String response = helper.calculateResponseMessage(message);

        kafkaProducer.sendMessage(response);

        System.out.println("Message Written To Topic: " + helper.shortMessage(message));
    }

}
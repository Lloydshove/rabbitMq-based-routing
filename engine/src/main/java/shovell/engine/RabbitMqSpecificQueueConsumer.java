package shovell.engine;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSpecificQueueConsumer {

    @Autowired KafkaProducer kafkaProducer;
    @Autowired MessageHelper helper;

    @RabbitListener(queuesToDeclare = @Queue(name ="#{podSpecificQueueName.name()}", durable = "true", exclusive = "true", autoDelete = "true"))
    public void consume(String message){
        System.out.println("Message read from RabbitMq:" + helper.shortMessage(message));

        String response = helper.calculateResponseMessage(message);

        kafkaProducer.sendMessage(response);

        System.out.println("Message Written To Topic: " + helper.shortMessage(message));
    }

}
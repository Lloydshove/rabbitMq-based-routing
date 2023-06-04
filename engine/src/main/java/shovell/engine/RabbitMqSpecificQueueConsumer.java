package shovell.engine;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class RabbitMqSpecificQueueConsumer {

    @Autowired KafkaProducer kafkaProducer;
    @Autowired MessageHelper helper;


    @RabbitListener(queuesToDeclare = @Queue(
            name ="#{podSpecificQueueName.name()}",
            autoDelete = "true"))
    public void consume(String message){
        System.out.println("Message read from RabbitMq:" + helper.shortMessage(message));

        String response = helper.calculateResponseFromId(message);

        kafkaProducer.sendMessage(response);

        System.out.println("Message Written To Topic: " + helper.shortMessage(response));
    }

}
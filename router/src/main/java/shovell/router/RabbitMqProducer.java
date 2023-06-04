package shovell.router;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired RabbitMqConfig config;

    public void sendMessage(String message){
        System.out.print("Send to queue : " + config.exchange  + " . ");
        rabbitTemplate.convertAndSend( config.exchange, config.routingKey, message);
    }

    public void sendMessage(String message, String queueName){
        System.out.print("Send with routing Key : " + queueName  + " . ");
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
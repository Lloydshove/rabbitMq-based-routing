package shovell.router;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {

    private String exchange = "statelessExchange";

    private String routingKey = "statelessKey";

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMessage(String message){

        rabbitTemplate.convertAndSend(
                exchange, routingKey, message);
    }
}
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

        if(hasTargetPodName(message)){
            String podName = extractPodName(message);
            rabbitMqProducer.sendMessage(message, podName);
        }
        else{
            rabbitMqProducer.sendMessage(message);
        }
        System.out.println("Sent Message to RabbitMq");
    }

    private String extractPodName(String message) {
        return message.replace("{", " ").replace("}", " ").replace("\"", "")
                .split(",")[2]
                .split(":")[1].trim();
    }

    private boolean hasTargetPodName(String message) {
        return message.contains("pod-name");
    }

    private static String shortMessage(String message) {
        return message.length() > 80 ? message.substring(0, 80) : message;
    }
}


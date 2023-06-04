package shovell.engine;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PodSpecificQueueName {

    private int number = new Random().nextInt(9999);
    public String name() {
        return "engine-number-" + number;
    }
}

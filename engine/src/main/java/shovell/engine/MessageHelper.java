package shovell.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper {

    @Autowired PodSpecificQueueName podNamer;

    public String calculateResponseMessage(String message) {
        int numberToFib = Integer.parseInt(message.split(",")[1].split(":")[1].trim());

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "{ \"resultingFib\": " + nthFibonacciTerm(numberToFib) + ", \"pod-name\" : \"" + podNamer.name() + "\" }";

    }

    private int nthFibonacciTerm(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return nthFibonacciTerm(n-1) + nthFibonacciTerm(n-2);
    }

    public String shortMessage(String message) {
        return message.length() < 70 ? message : message.substring(0, 70);
    }

}

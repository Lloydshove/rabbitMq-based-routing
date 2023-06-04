package shovell.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper {

    @Autowired PodSpecificQueueName podNamer;
    @Autowired PayloadCache payloadCache;

    public String calculateResponseMessage(String message) {
        int numberToFib = Integer.parseInt(message.split(",")[1].split(":")[1].trim());

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        payloadCache.put(getIdFrom(message), message);

        return "{ \"resultingFib\": " + nthFibonacciTerm(numberToFib) + ", \"pod-name\" : \"" + podNamer.name() + "\" }";
    }

    public String calculateResponseFromId(String message) {
        return calculateResponseMessage(payloadCache.get(getIdFrom(message)));
    }

    private String getIdFrom(String message) {
        return message.replace("\"", "").split(",")[0].split(":")[1].replace(",", "").trim();
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

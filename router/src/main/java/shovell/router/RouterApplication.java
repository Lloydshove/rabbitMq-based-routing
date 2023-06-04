package shovell.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RouterApplication {

    public RouterApplication(){
        new KafkaConsumer();
    }

    public static void main(String[] args) {
        System.out.println("Starting Main");
        SpringApplication.run(RouterApplication.class, args);
    }

}

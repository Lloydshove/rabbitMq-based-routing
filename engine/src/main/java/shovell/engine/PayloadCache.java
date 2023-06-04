package shovell.engine;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PayloadCache {

    private HashMap<String, String> store = new HashMap<>();
    public void put(String id, String message) {
        store.put(id, message);
        System.out.println("Saved Payload : " + id);
    }

    public String get(String id){
        System.out.println("Loaded Payload : " + id );
        return store.get(id);
    }
}

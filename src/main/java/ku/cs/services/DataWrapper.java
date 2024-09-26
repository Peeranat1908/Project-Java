package ku.cs.services;

import java.util.HashMap;

public class DataWrapper {
    private HashMap<String, Object> objects;

    // Constructor initializing the HashMap
    public DataWrapper() {
        objects = new HashMap<>();
    }

    // Method to add an object with a specific key
    public void addObject(String key, Object object) {
        objects.put(key, object);
    }

    public Object getObject(String key) {
        if (! objects.containsKey(key)) {
            System.err.println("Key not found");
        }
        return objects.get(key);
    }

    @Override
    public String toString() {
        return "DataWrapper{" +
                "objects=" + objects +
                '}';
    }
}
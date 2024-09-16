package ku.cs.services;

import java.util.HashMap;

public class ElementsWrapper {
    private HashMap<String, Object> objects;

    public ElementsWrapper() {
        objects = new HashMap<>();
    }

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
        return "ObjectWrapper{" +
                "objects=" + objects +
                '}';
    }
}

package medium.src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Storage {
    private static Storage instance;
    private Map<String, Queue<String>> table = new HashMap<>();

    private Storage() {
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public synchronized Map<String, Queue<String>> getTable() {
        return table;
    }

    public synchronized void register(String origin) {
        this.getTable().put(origin, new LinkedList<>());
    }

    public synchronized void writeRequest(String origin, String request) {
        System.out.println("Writing request " + request + " in storage for " + this.getTable());
        Queue<String> queue = this.getTable().get(origin);
        if (queue == null) {
            try {
                throw new Exception("Storage not registered for " + origin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.getTable().get(origin).add(request);
        System.out.println("Written request " + request + " in storage for " +
                origin);
    }

    public synchronized String readRequest(String origin) {
        String request = this.getTable().get(origin).poll();
        return request;
    }
}

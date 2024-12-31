package backend.src;

import medium.src.Storage;

class Servlet implements Runnable {
    private String origin;
    private Storage storage;

    public Servlet(String domain, Integer port, Storage storage) {
        this.origin = domain + ":" + port;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            String request = storage.readRequest(origin);
            if (request != null) {
                System.out.println("Request " + request + " received on " + origin);
            }
            // System.out.println(origin + " : " + storage.getTable());
        }
    }
}

public class Server {
    private String domain;
    private Integer port;
    private Storage storage;

    public Server(String domain, Integer port, Storage storage) {
        this.domain = domain;
        this.port = port;
        this.storage = storage;
    }

    public String getDomain() {
        return domain;
    }

    public Integer getPort() {
        return port;
    }

    public void execute() {
        Runnable servlet = new Servlet(domain, port, storage);
        Thread container = new Thread(servlet);
        container.start();
        System.out.println("Server listening at " + domain + ":" + port);
    }
}

package frontend.src;

import medium.src.Storage;

public class Client implements Runnable {
    private Storage storage;

    public Client(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        System.out.println("Client executing");
        System.out.println("Client using Storage: " + storage.hashCode());
        storage.writeRequest("localhost:8080", "A");
        storage.writeRequest("localhost:8081", "B");
        storage.writeRequest("localhost:8080", "C");
        storage.writeRequest("localhost:8081", "D");
    }
}

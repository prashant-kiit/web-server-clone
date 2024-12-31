package backend.src;

import medium.src.Storage;

public class Machine implements Runnable {
    private Storage storage;

    public Machine(Storage storage) {
        this.storage = storage;
    }

    public void registerInStorage(Server server) {
        System.out.println("Machine using Storage: " + storage.hashCode());
        storage.register(server.getDomain() + ":" + server.getPort());
    }

    @Override
    public void run() {
        System.out.println("Machine executing...");
        Server server1 = new Server("localhost", 8080, storage);
        registerInStorage(server1);
        server1.execute();
        Server server2 = new Server("localhost", 8081, storage);
        registerInStorage(server2);
        server2.execute();
    }
}

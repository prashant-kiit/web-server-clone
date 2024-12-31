package application.src;

import backend.src.Machine;
import frontend.src.Client;
import medium.src.Storage;

public class Index {
    public static void main(String[] args) {
        System.out.println("Application executing...");
        Storage storage = Storage.getInstance();
        new Thread(new Machine(storage)).start();
        try {
            Thread.sleep(5000); // Sleep for 5 seconds (5000 milliseconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Client(storage)).start();
    }
}

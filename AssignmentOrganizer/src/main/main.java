package main;

import java.io.File;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        File file = new File("assignments.json");
        Organizer main = new Organizer(file);
        main.run();
    }
}

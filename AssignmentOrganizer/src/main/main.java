package main;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application{

    public void start( Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
        primaryStage.setTitle("Organizer");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();


    }
    public static void main(String[] args) throws IOException {

       // launch(args);
        File file = new File("assignments.json");
        Organizer main = new Organizer(file);

        main.run();

    }
}

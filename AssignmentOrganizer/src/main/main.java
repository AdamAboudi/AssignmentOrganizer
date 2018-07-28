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
        Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/root.fxml"));
        primaryStage.setTitle("Organizer");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }
    public static void main(String[] args) throws IOException {


        File file = new File("assignments.json");
        Organizer organizer = new Organizer(file);
        launch(args);
       // organizer.run();

    }
}

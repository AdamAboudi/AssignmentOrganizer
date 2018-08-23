package main;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.assignment.types.SuperAssignment;


public class main extends Application{

    private static Path assignmentPath;
    public static HashMap<String, SuperAssignment> AssignmentMap = new HashMap<>();
    private static final Gson gson = new Gson();

    public void start( Stage primaryStage) throws Exception{
        Parent root;
        root = FXMLLoader.load(getClass().getResource("java/resources/root.fxml"));
        primaryStage.setTitle("AssignmentOrganizer");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {

        File file = new File("assignments.json");
        file.createNewFile();
        assignmentPath =  Paths.get(file.getAbsolutePath());


        JsonReader reader = new JsonReader(new FileReader(assignmentPath.toString()));
        SuperAssignment[] readAsArray = gson.fromJson(reader, SuperAssignment[].class);

        if (readAsArray != null) {
            for (SuperAssignment assignment : readAsArray) {
                AssignmentMap.put(assignment.getName(), assignment);
            }

        }
        launch(args);

    }

    /**
     *  Writes the Current AssignmentList to Assignments.json
     * @throws UnsupportedEncodingException e
     * @throws FileNotFoundException e
     * @throws IOException e
     */
    public static void updateFile() throws UnsupportedEncodingException,
            FileNotFoundException, IOException {

        try (FileWriter filex = new FileWriter(assignmentPath.toString())) {
            SuperAssignment[] temp =  AssignmentMap.values().toArray(
                    new SuperAssignment[AssignmentMap.size()]);
            String json = gson.toJson(temp);
            filex.write(json);
        }
    }
}

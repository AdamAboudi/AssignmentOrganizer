package main.java.Controllers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import main.java.assignment.types.SuperAssignment;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import main.Organizer;


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class rootController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private ListView<SuperAssignment> MainList;

    @FXML
    private Button completedButton;

    @FXML
    private MenuItem NewAssignment;

    @FXML
    private MenuItem NewPaper;

    @FXML
    private MenuItem NewProject;

    @FXML
    private MenuItem NewReading;

   private  List<SuperAssignment> list = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshList();

        completedButton.setVisible(false);

        NewPaper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane panel = null;
                try {
                    panel = FXMLLoader.load(getClass().getResource("../resources/newPaper.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pane.getChildren().setAll(panel);

            }
        });

        NewAssignment.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            AnchorPane panel = null;
            try {
                panel = FXMLLoader.load(getClass().getResource("../resources/newAssignment.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            pane.getChildren().setAll(panel);

        }
    });

        NewProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane panel = null;
                try {
                    panel = FXMLLoader.load(getClass().getResource("../resources/newProject.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pane.getChildren().setAll(panel);

            }
        });

        NewReading.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane panel = null;
                try {
                    panel = FXMLLoader.load(getClass().getResource("../resources/newReading.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pane.getChildren().setAll(panel);

            }
        });

    completedButton.setOnAction(event -> {
        for(int i = 0; i < list.size(); i++){
            Organizer.AssignmentMap.remove(list.get(i).getName());
        }

        list.clear();
        refreshList();
    });
    }


    public void refreshList(){

        try {
            Organizer.updateFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<SuperAssignment> assignmentList = new ArrayList<>(Organizer.AssignmentMap.values());

        ObservableList<SuperAssignment> items = MainList.getItems();
        MainList.getItems().clear();
        items.addAll(assignmentList);
        MainList.setItems(items);

        MainList.setCellFactory(CheckBoxListCell.forListView(item -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) ->
                    {

                        completedButton.setVisible(true);
                        if(isNowSelected){
                            list.add(item);
                        }
                        else{
                            list.remove(item);
                        }
                    }
            );

            return observable;
        }));
    }
}

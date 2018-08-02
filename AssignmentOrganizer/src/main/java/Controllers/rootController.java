package main.java.Controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class rootController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private ListView<SuperAssignment> MainList;

    @FXML
    private Button completedButton;

    @FXML
    private MenuItem NewAssignment;

   private ArrayList<SuperAssignment> assignmentList = new ArrayList<>(Organizer.AssignmentMap.values());

   private  List<SuperAssignment> list = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshList();



        completedButton.setVisible(false);

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

    completedButton.setOnAction(event -> {
        for(int i = 0; i < list.size(); i++){
            System.out.println("Removing "+list.get(i));
            assignmentList.remove(list.get(i));
        }

        refreshList();

    });
    }


    public void refreshList(){
        ObservableList<SuperAssignment> items = MainList.getItems();
        items.clear();
        items.addAll(assignmentList);
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

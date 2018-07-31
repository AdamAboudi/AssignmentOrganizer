package main;

import assignment.types.SuperAssignment;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class rootController implements Initializable {

    @FXML
    private ListView<SuperAssignment> MainList;

    @FXML
    private Button completedButton;

   private ArrayList<SuperAssignment> assignmentList = new ArrayList<>(Organizer.AssignmentMap.values());

   private  List<SuperAssignment> list = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        completedButton.setVisible(false);

        refreshList();


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

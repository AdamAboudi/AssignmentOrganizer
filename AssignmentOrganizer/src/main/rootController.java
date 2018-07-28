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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class rootController implements Initializable {

    @FXML
    private ListView<String> MainList;

    @FXML
    private Button completedButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ObservableList<String> items = MainList.getItems();
        completedButton.setVisible(false);

        ArrayList<SuperAssignment> assignmentList =
                new ArrayList<>(Organizer.AssignmentMap.values());

        ArrayList<String> NameList = new ArrayList<>();

        for (int i = 0; i < assignmentList.size(); i++) {
            NameList.add((i + 1 + ") " + assignmentList.get(i).getName()));
        }

        items.addAll(NameList);

        MainList.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(String item) {
                BooleanProperty observable = new SimpleBooleanProperty();
                 observable.addListener((obs, wasSelected, isNowSelected) ->
                         {
                             completedButton.setVisible(true);
                             System.out.println("Checkbox for " + item + " was selected");
                         }
                 );

                return observable;
            }
        }));

    }
}

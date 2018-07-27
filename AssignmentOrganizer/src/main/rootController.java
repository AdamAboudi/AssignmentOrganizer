package main;

import assignment.types.SuperAssignment;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class rootController implements Initializable {

    @FXML
    private ListView<String> MainList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ObservableList<String> items = MainList.getItems();


        ArrayList<SuperAssignment> assignmentList =
                new ArrayList<>(Organizer.AssignmentMap.values());

        ArrayList<String> NameList = new ArrayList<>();

        for (int i = 0; i < assignmentList.size(); i++) {
            NameList.add((i + 1 + ") " + assignmentList.get(i).getName()));
        }
        items.addAll(NameList);

    }
}

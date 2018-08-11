package main.java.Controllers;

import javafx.fxml.Initializable;
import main.Organizer;
import main.java.assignment.types.SuperAssignment;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class newPaperController implements Initializable {


    private ArrayList<SuperAssignment> assignmentList = new ArrayList<>(Organizer.AssignmentMap.values());

    @Override
    public void initialize(URL location, ResourceBundle resources){



    }
}

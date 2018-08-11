package main.java.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import main.java.assignment.types.Project;



import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static main.Organizer.AssignmentMap;

public class newProjectController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private ChoiceBox<Integer> PrioChoiceBox;

    @FXML
    private ChoiceBox<Integer> DiffChoiceBox;

    @FXML
    private TextField NameTextField;

    @FXML
    private DatePicker DueDatePicker;

    @FXML
    private Button AcceptButton;

    @FXML
    private Button BackButton;

    @FXML
    private CheckBox GroupCheckBox;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ObservableList<Integer> oneToTen = FXCollections.observableArrayList();
        oneToTen.addAll(1,2,3,4,5,6,7,8,9,10);
        PrioChoiceBox.setItems(oneToTen);
        DiffChoiceBox.setItems(oneToTen);

        BackButton.setOnAction(event -> {

            AnchorPane panel = null;
            try {
                panel = FXMLLoader.load(getClass().getResource("../resources/root.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            pane.getChildren().setAll(panel);


        });
        AcceptButton.setOnAction(event -> {
            if(PrioChoiceBox.getValue() != null &&
                    DiffChoiceBox.getValue() != null &&
                    NameTextField.getText() != null &&
                    DueDatePicker.getValue() != null){

                System.out.println(NameTextField.getText());
                AssignmentMap.put(NameTextField.getText(),
                        new Project("Project",
                                NameTextField.getText(),
                                DueDatePicker.getValue(),
                                PrioChoiceBox.getValue(),
                                DiffChoiceBox.getValue(),
                                GroupCheckBox.isSelected()));

                AnchorPane panel = null;
                try {
                    panel = FXMLLoader.load(getClass().getResource("../resources/root.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pane.getChildren().setAll(panel);
            }
        });

    }
}

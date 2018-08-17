package main.java.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import main.java.assignment.types.Reading;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static main.Organizer.AssignmentMap;

public class newReadingController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private ChoiceBox<Integer> PrioChoiceBox;

    @FXML
    private TextField LengthTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private DatePicker DueDatePicker;

    @FXML
    private TextField CompTimeTextField;

    @FXML
    private Button AcceptButton;

    @FXML
    private Button BackButton;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ObservableList<Integer> oneToTen = FXCollections.observableArrayList();
        oneToTen.addAll(1,2,3,4,5,6,7,8,9,10);
        PrioChoiceBox.setItems(oneToTen);

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        CompTimeTextField.setTextFormatter(textFormatter);
        LengthTextField.setTextFormatter(textFormatter);

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
                    LengthTextField.getText() != null &&
                    CompTimeTextField.getText() != null &&
                    NameTextField.getText() != null &&
                    DueDatePicker.getValue() != null){

                AssignmentMap.put(NameTextField.getText(),
                        new Reading("Reading",
                                NameTextField.getText(),
                                DueDatePicker.getValue(),
                                PrioChoiceBox.getValue(),
                                Integer.parseInt(LengthTextField.getText()),
                                Integer.parseInt(CompTimeTextField.getText())));
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

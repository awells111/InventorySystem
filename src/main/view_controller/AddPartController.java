package main.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddPartController {

    @FXML
    private ToggleGroup toglegroupInHouse;

    @FXML
    private Label labelPartID;

    @FXML
    private TextField textfieldPartName;

    @FXML
    private TextField textfieldPartInv;

    @FXML
    private TextField textfieldPartPrice;

    @FXML
    private TextField textfieldPartMin;

    @FXML
    private TextField textfieldPartMax;

    @FXML
    private TextField textfieldPartMachineID;

    @FXML
    void handlePartSave(ActionEvent event) {
        //todo handle selected RadioButton here?
    }

    @FXML
    void handlePartCancel(ActionEvent event) {

    }

}

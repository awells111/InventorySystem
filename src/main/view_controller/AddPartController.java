package main.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.model.Part;

public class AddPartController {

    public static final String FXML_ADD_PART = "view_controller/AddPart.fxml";

    @FXML
    private ToggleGroup togglegroupInHouse;

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

    private Stage dialogStage;
    private Part part;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPart(Part part) {
        this.part = part;

        labelPartID.setText(Integer.toString(part.getPartID()));
        textfieldPartName.setText(part.getName());
        textfieldPartInv.setText(Integer.toString(part.getInStock()));
        textfieldPartPrice.setText(Double.toString(part.getPrice()));
        textfieldPartMin.setText(Integer.toString(part.getMin()));
        textfieldPartMax.setText(Integer.toString(part.getMax()));
        textfieldPartMachineID.setText("fix this");

        //todo setText for inhouse or outsourced part
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    void handlePartSave(ActionEvent event) {
        //todo handle selected RadioButton here?
    }

    @FXML
    void handlePartCancel(ActionEvent event) {
        dialogStage.close();
    }

}

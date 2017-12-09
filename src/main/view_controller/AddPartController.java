package main.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.model.InhousePart;
import main.model.Inventory;
import main.model.OutsourcedPart;
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

    public void setPart(Inventory inventory, Part part) {
        this.part = part;
        //todo fix radio group
        labelPartID.setText(Integer.toString(inventory.getPartCount()));
        textfieldPartName.setText(part.getName());
        textfieldPartInv.setText(Integer.toString(part.getInStock()));
        textfieldPartPrice.setText(Double.toString(part.getPrice()));
        textfieldPartMin.setText(Integer.toString(part.getMin()));
        textfieldPartMax.setText(Integer.toString(part.getMax()));

        if (isInhouse()) {
            textfieldPartMachineID.setText(Integer.toString(((InhousePart) part).getMachineID()));
        } else if (isOutsourced()) {
            textfieldPartMachineID.setText(((OutsourcedPart) part).getCompanyName());
        } else {
            textfieldPartMachineID.setText("");
        }
    }

    @FXML
    void handlePartSave(ActionEvent event) {
        //todo if checkbox is different than part type, set new part type

        part.setPartID(Integer.parseInt(labelPartID.getText()));
        part.setName(textfieldPartName.getText());
        part.setInStock(Integer.parseInt(textfieldPartInv.getText()));
        part.setPrice(Double.parseDouble(textfieldPartPrice.getText()));
        part.setMin(Integer.parseInt(textfieldPartMin.getText()));
        part.setMax(Integer.parseInt(textfieldPartMax.getText()));

        if (isSaveClicked() && isInhouse()) {
            ((InhousePart) part).setMachineID(Integer.parseInt(textfieldPartMachineID.getText()));
        } else if (isSaveClicked() && isOutsourced()) {
            ((OutsourcedPart) part).setCompanyName(textfieldPartMachineID.getText());
        }

        setSaveClicked();
        dialogStage.close();
    }

    @FXML
    void handlePartCancel(ActionEvent event) {
        dialogStage.close();
    }

    private void setSaveClicked() {
        this.saveClicked = true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    private boolean isInhouse() {
        return part instanceof InhousePart;
    }

    private boolean isOutsourced() {
        return part instanceof OutsourcedPart;
    }
}

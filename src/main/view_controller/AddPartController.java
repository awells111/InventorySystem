package main.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.model.InhousePart;
import main.model.Inventory;
import main.model.OutsourcedPart;
import main.model.Part;

public class AddPartController {

    public static final String FXML_ADD_PART = "view_controller/AddPart.fxml";

    @FXML
    private RadioButton radioButtonInHouse;

    @FXML
    private RadioButton radioButtonOutSourcecd;

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
    private Inventory inventory;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPart(Inventory inventory, Part part) {
        this.inventory = inventory;
        this.part = part;

        labelPartID.setText(Integer.toString(this.inventory.getPartCount()));
        textfieldPartName.setText(part.getName());
        textfieldPartInv.setText(Integer.toString(part.getInStock()));
        textfieldPartPrice.setText(Double.toString(part.getPrice()));
        textfieldPartMin.setText(Integer.toString(part.getMin()));
        textfieldPartMax.setText(Integer.toString(part.getMax()));

        if (partIsInhouse()) {
            radioButtonInHouse.selectedProperty().set(true);
            textfieldPartMachineID.setText(Integer.toString(((InhousePart) part).getMachineID()));
        } else if (partIsOutsourced()) {
            radioButtonOutSourcecd.selectedProperty().set(true);
            textfieldPartMachineID.setText(((OutsourcedPart) part).getCompanyName());
        } else {
            textfieldPartMachineID.setText("");
        }
    }

    @FXML
    void handlePartSave(ActionEvent event) {

        if(radioButtonInHouse.isSelected() && partIsOutsourced()) {
            part = new InhousePart();
        } else if (radioButtonOutSourcecd.isSelected() && partIsInhouse()) {
            part = new OutsourcedPart();
        }

        if (isSaveClicked() && partIsInhouse()) {
            ((InhousePart) part).setMachineID(Integer.parseInt(textfieldPartMachineID.getText()));
        } else if (isSaveClicked() && partIsOutsourced()) {
            ((OutsourcedPart) part).setCompanyName(textfieldPartMachineID.getText());
        }

        part.setPartID(Integer.parseInt(labelPartID.getText()));
        part.setName(textfieldPartName.getText());
        part.setInStock(Integer.parseInt(textfieldPartInv.getText()));
        part.setPrice(Double.parseDouble(textfieldPartPrice.getText()));
        part.setMin(Integer.parseInt(textfieldPartMin.getText()));
        part.setMax(Integer.parseInt(textfieldPartMax.getText()));
        
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

    private boolean partIsInhouse() {
        return part instanceof InhousePart;
    }

    private boolean partIsOutsourced() {
        return part instanceof OutsourcedPart;
    }
}

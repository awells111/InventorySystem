package main.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.InhousePart;
import main.model.Inventory;
import main.model.OutsourcedPart;
import main.model.Part;

import static main.util.NumberUtil.isDouble;
import static main.util.NumberUtil.isInteger;

public class AddPartController {

    public static final String FXML_ADD_PART = "view_controller/AddPart.fxml";

    @FXML
    private RadioButton radioButtonInHouse;

    @FXML
    private RadioButton radioButtonOutSourced;

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

        if (part.getPartID() == -1) { //If this is a new part, use inventory.getPartCount to set its partID
            labelPartID.setText(Integer.toString(this.inventory.getPartCount()));
        } else { //Else use the original partID
            labelPartID.setText(Integer.toString(part.getPartID()));
        }

        textfieldPartName.setText(part.getName());
        textfieldPartInv.setText(Integer.toString(part.getInStock()));
        textfieldPartPrice.setText(Double.toString(part.getPrice()));
        textfieldPartMin.setText(Integer.toString(part.getMin()));
        textfieldPartMax.setText(Integer.toString(part.getMax()));

        if (partIsInhouse()) {
            radioButtonInHouse.selectedProperty().set(true);
            textfieldPartMachineID.setText(Integer.toString(((InhousePart) part).getMachineID()));
        } else if (partIsOutsourced()) {
            radioButtonOutSourced.selectedProperty().set(true);
            textfieldPartMachineID.setText(((OutsourcedPart) part).getCompanyName());
        } else {
            textfieldPartMachineID.setText("");
        }
    }

    @FXML
    void handlePartSave() {
        if(errorBeforeSave()) {
            return;
        }
        if (radioButtonInHouse.isSelected()) {
            part = new InhousePart();
            ((InhousePart) part).setMachineID(Integer.parseInt(textfieldPartMachineID.getText()));
        } else if (radioButtonOutSourced.isSelected()) {
            part = new OutsourcedPart();
            ((OutsourcedPart) part).setCompanyName(textfieldPartMachineID.getText());
        }

        part.setPartID(Integer.parseInt(labelPartID.getText()));
        part.setName(textfieldPartName.getText());
        part.setInStock(Integer.parseInt(textfieldPartInv.getText()));
        part.setPrice(Double.parseDouble(textfieldPartPrice.getText()));
        part.setMin(Integer.parseInt(textfieldPartMin.getText()));
        part.setMax(Integer.parseInt(textfieldPartMax.getText()));

        if (!isNewPart()) {
            inventory.updatePart(part);
        } else if (isNewPart()) {
            inventory.addPart(part);
        }

        dialogStage.close();
    }

    @FXML
    void handlePartCancel() {
        dialogStage.close();
    }

    private boolean partIsInhouse() {
        return part instanceof InhousePart;
    }

    private boolean partIsOutsourced() {
        return part instanceof OutsourcedPart;
    }

    private boolean isNewPart() {
        return part.getPartID() == inventory.getPartCount();
    }

    private boolean errorBeforeSave() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Saving Part");
        alert.setHeaderText(null);

        //Display alert for incorrect inputs
        if (textfieldPartName.getText().equals("")) {
            alert.setContentText("Name cannot be empty");
            alert.showAndWait();
            return true;
        }

        if (!isInteger(textfieldPartInv.getText())) {
            alert.setContentText("Inv must be an integer");
            alert.showAndWait();
            return true;
        }

        if (!isDouble(textfieldPartPrice.getText())) {
            alert.setContentText("Price/Cost must be a number");
            alert.showAndWait();
            return true;
        }

        if (!isInteger(textfieldPartMin.getText())) {
            alert.setContentText("Min must be an integer");
            alert.showAndWait();
            return true;
        }

        if (!isInteger(textfieldPartMax.getText())) {
            alert.setContentText("Max must be an integer");
            alert.showAndWait();
            return true;
        }

        if (radioButtonInHouse.isSelected() && !isInteger(textfieldPartMachineID.getText())) {
            alert.setContentText("Machine ID must be an integer");
            alert.showAndWait();
            return true;
        } else if (radioButtonOutSourced.isSelected() && textfieldPartMachineID.getText().equals("")) {
            alert.setContentText("Company Name cannot be empty");
            alert.showAndWait();
            return true;
        }


        int inv = Integer.parseInt(textfieldPartInv.getText());
        int min = Integer.parseInt(textfieldPartMin.getText());
        int max = Integer.parseInt(textfieldPartMax.getText());

        if (min > max || max < min) { //max < min is redundant but I am including it to meet project specifications
            alert.setContentText("Min cannot be higher than Max");
            alert.showAndWait();
            return true;
        }

        if (inv < min || inv > max) {
            alert.setContentText("Inv must be an integer between Min and Max");
            alert.showAndWait();
            return true;
        }

        return false;
    }
}

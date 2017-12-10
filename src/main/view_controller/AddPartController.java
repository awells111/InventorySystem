package main.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

        setSaveClicked();
        dialogStage.close();
    }

    @FXML
    void handlePartCancel() {
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

    private boolean isNewPart() {
        return part.getPartID() == inventory.getPartCount();
    }
}

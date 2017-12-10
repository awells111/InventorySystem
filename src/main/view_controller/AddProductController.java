package main.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.*;

public class AddProductController {

    public static final String FXML_ADD_PRODUCT = "view_controller/AddProduct.fxml";

    @FXML
    private Label labelProductID;

    @FXML
    private TextField textfieldProductName;

    @FXML
    private TextField textfieldProductInv;

    @FXML
    private TextField textfieldProductPrice;

    @FXML
    private TextField textfieldProductMin;

    @FXML
    private TextField textfieldProductMax;

    @FXML
    private TextField textfieldSearch;

    @FXML
    private TableView<Part> tableviewAddPart;

    @FXML
    private TableColumn<Part, Integer> columnAddPartID;

    @FXML
    private TableColumn<Part, String> columnAddPartName;

    @FXML
    private TableColumn<Part, Integer> columnAddPartLevel;

    @FXML
    private TableColumn<Part, Double> columnAddPartPrice;

    @FXML
    private TableView<Product> tableviewProductPart;

    @FXML
    private TableColumn<Part, Integer> columnProductPartID;

    @FXML
    private TableColumn<Part, String> columnProductPartName;

    @FXML
    private TableColumn<Part, Integer> columnProductPartLevel;

    @FXML
    private TableColumn<Part, Double> columnProductPartPrice;

    private Stage dialogStage;
    private Product product;
    private boolean saveClicked = false;
    private Inventory inventory;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Inventory inventory, Product product) {
        this.inventory = inventory;
        this.product = product;

        if (product.getProductID() == -1) { //If this is a new product, use inventory.getProductCount to set its productID
            labelProductID.setText(Integer.toString(this.inventory.getProductCount()));
        } else { //Else use the original productID
            labelProductID.setText(Integer.toString(product.getProductID()));
        }

        textfieldProductName.setText(product.getName());
        textfieldProductInv.setText(Integer.toString(product.getInStock()));
        textfieldProductPrice.setText(Double.toString(product.getPrice()));
        textfieldProductMin.setText(Integer.toString(product.getMin()));
        textfieldProductMax.setText(Integer.toString(product.getMax()));
    }

    @FXML
    void handleSearchPart(ActionEvent event) {

    }

    @FXML
    void handleAddPart(ActionEvent event) {

    }

    @FXML
    void handleDeletePart(ActionEvent event) {

    }

    @FXML
    void handleProductSave(ActionEvent event) {

    }

    @FXML
    void handleProductCancel(ActionEvent event) {

    }
}

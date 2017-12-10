package main.view_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.*;

import java.util.Observable;

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
    private TableView<Part> tableviewProductPart;

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
    private Inventory inventory;
    ObservableList<Part> parts;

    @FXML
    private void initialize() {
        columnAddPartID.setCellValueFactory(
                cellData -> cellData.getValue().partIDProperty().asObject()
        );

        columnAddPartName.setCellValueFactory(
                cellData -> cellData.getValue().partNameProperty()
        );

        columnAddPartLevel.setCellValueFactory(
                cellData -> cellData.getValue().partInStockProperty().asObject()
        );

        columnAddPartPrice.setCellValueFactory(
                cellData -> cellData.getValue().partPriceProperty().asObject()
        );

        columnProductPartID.setCellValueFactory(
                cellData -> cellData.getValue().partIDProperty().asObject()
        );

        columnProductPartName.setCellValueFactory(
                cellData -> cellData.getValue().partNameProperty()
        );

        columnProductPartLevel.setCellValueFactory(
                cellData -> cellData.getValue().partInStockProperty().asObject()
        );

        columnProductPartPrice.setCellValueFactory(
                cellData -> cellData.getValue().partPriceProperty().asObject()
        );
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

    public void initTables() {
        parts = FXCollections.observableArrayList(product.getAssociatedParts());
        tableviewProductPart.setItems(parts);
    }
    @FXML
    void handleAddPart(ActionEvent event) {

    }

    @FXML
    void handleDeletePart(ActionEvent event) {

    }

    @FXML
    void handleProductSave(ActionEvent event) {
        product.setProductID(Integer.parseInt(labelProductID.getText()));
        product.setName(textfieldProductName.getText());
        product.setInStock(Integer.parseInt(textfieldProductInv.getText()));
        product.setPrice(Double.parseDouble(textfieldProductPrice.getText()));
        product.setMin(Integer.parseInt(textfieldProductMin.getText()));
        product.setMax(Integer.parseInt(textfieldProductMax.getText()));

        if (!isNewProduct()) {
            inventory.updateProduct(product);
        } else if (isNewProduct()) {
            inventory.addProduct(product);
        }

        dialogStage.close();
    }

    @FXML
    void handleProductCancel(ActionEvent event) {
        dialogStage.close();
    }

    private boolean isNewProduct() {
        return product.getProductID() == inventory.getProductCount();
    }
}

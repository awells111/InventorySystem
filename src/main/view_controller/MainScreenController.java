package main.view_controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.Main;
import main.model.InhousePart;
import main.model.Part;
import main.model.Product;

public class MainScreenController {

    public static final String FXML_MAIN_SCREEN = "view_controller/MainScreen.fxml";

    private Main mainApp;

    @FXML
    private TextField textfieldPart;

    @FXML
    private TextField textfieldProduct;

    @FXML
    private TableView<Part> tableviewPart;

    @FXML
    private TableColumn<Part, Integer> columnPartID;

    @FXML
    private TableColumn<Part, String> columnPartName;

    @FXML
    private TableColumn<Part, Integer> columnPartLevel;

    @FXML
    private TableColumn<Part, Double> columnPartPrice;

    @FXML
    private TableView<Product> tableviewProduct;

    @FXML
    private TableColumn<Product, Integer> columnProductID;

    @FXML
    private TableColumn<Product, String> columnProductName;

    @FXML
    private TableColumn<Product, Integer> columnProductLevel;

    @FXML
    private TableColumn<Product, Double> columnProductPrice;

    @FXML
    private void initialize() {

        //Need to add .asObject() for non-Strings in JavaFX
        columnPartID.setCellValueFactory(
                cellData -> cellData.getValue().partIDProperty().asObject()
        );

        columnPartName.setCellValueFactory(
                cellData -> cellData.getValue().partNameProperty()
        );

        columnPartLevel.setCellValueFactory(
                cellData -> cellData.getValue().partInStockProperty().asObject()
        );

        columnPartPrice.setCellValueFactory(
                cellData -> cellData.getValue().partPriceProperty().asObject()
        );

        columnProductID.setCellValueFactory(
                cellData -> cellData.getValue().productIDProperty().asObject()
        );

        columnProductName.setCellValueFactory(
                cellData -> cellData.getValue().productNameProperty()
        );

        columnProductLevel.setCellValueFactory(
                cellData -> cellData.getValue().productInStockProperty().asObject()
        );

        columnProductPrice.setCellValueFactory(
                cellData -> cellData.getValue().productPriceProperty().asObject()
        );
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        tableviewPart.setItems(mainApp.getInventory().getAllParts());
        tableviewProduct.setItems(mainApp.getInventory().getProducts());
    }

    @FXML
    void handleSearchPart(ActionEvent event) {

    }

    @FXML
    void handleDeletePart(ActionEvent event) {
        int selectedIndex = tableviewPart.getSelectionModel().getSelectedIndex();
        tableviewPart.getItems().remove(selectedIndex);
    }

    @FXML
    void handleModifyPart(ActionEvent event) {
        Part selectedPart = tableviewPart.getSelectionModel().getSelectedItem();
        mainApp.showAddPart(selectedPart);
    }

    @FXML
    void handleAddPart(ActionEvent event) {
        Part newPart = new InhousePart();
        mainApp.showAddPart(newPart);
    }

    @FXML
    void handleSearchProduct(ActionEvent event) {

    }

    @FXML
    void handleDeleteProduct(ActionEvent event) {
        int selectedIndex = tableviewProduct.getSelectionModel().getSelectedIndex();
        tableviewProduct.getItems().remove(selectedIndex);
    }

    @FXML
    void handleModifyProduct(ActionEvent event) {

    }

    @FXML
    void handleAddProduct(ActionEvent event) {
        mainApp.showAddProduct();
    }

    @FXML
    void handleExit(ActionEvent event) {
        Platform.exit();
    }

}

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

        tableviewPart.setItems(mainApp.getPartData());
        tableviewProduct.setItems(mainApp.getProductData());
    }

    @FXML
    void handleSearchPart(ActionEvent event) {

    }

    @FXML
    void handleDeletePart(ActionEvent event) {

    }

    @FXML
    void handleModifyPart(ActionEvent event) {

    }

    @FXML
    void handleAddPart(ActionEvent event) {
        //todo have default set to inhousepart

        //todo delete sample part
        mainApp.showAddPart(new InhousePart(9, "I", 1.0, 5, 1, 5, 10));
    }

    @FXML
    void handleSearchProduct(ActionEvent event) {

    }

    @FXML
    void handleDeleteProduct(ActionEvent event) {

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

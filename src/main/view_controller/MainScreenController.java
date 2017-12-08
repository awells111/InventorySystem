package main.view_controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.model.Part;
import main.model.Product;

public class MainScreenController {

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

    }

    @FXML
    void handleExit(ActionEvent event) {
        Platform.exit();
    }

}

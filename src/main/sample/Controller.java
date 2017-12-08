package main.sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TableView<?> tableviewProduct;

    @FXML
    private Button buttonExit;

    @FXML
    private TextField textfieldProduct;

    @FXML
    private Button buttonPartSearch;

    @FXML
    private TableColumn<?, ?> columnPartLevel;

    @FXML
    private Button buttonPartModify;

    @FXML
    private Button buttonProductDelete;

    @FXML
    private Button buttonProductAdd;

    @FXML
    private TableColumn<?, ?> columnPartPrice;

    @FXML
    private TableColumn<?, ?> columnPartName;

    @FXML
    private TextField textfieldPart;

    @FXML
    private TableColumn<?, ?> columnPartID;

    @FXML
    private TableColumn<?, ?> columnProductLevel;

    @FXML
    private Button buttonProductModify;

    @FXML
    private TableView<?> tableviewPart;

    @FXML
    private Button buttonPartDelete;

    @FXML
    private TableColumn<?, ?> columnProductPrice;

    @FXML
    private Button buttonProductSearch;

    @FXML
    private Button buttonPartAdd;

    @FXML
    private TableColumn<?, ?> columnProductID;

    @FXML
    private TableColumn<?, ?> columnProductName;

    @FXML
    void actionSearchPart(ActionEvent event) {

    }

    @FXML
    void actionDeletePart(ActionEvent event) {

    }

    @FXML
    void actionModifyPart(ActionEvent event) {

    }

    @FXML
    void actionAddPart(ActionEvent event) {

    }

    @FXML
    void actionSearchProduct(ActionEvent event) {

    }

    @FXML
    void actionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void actionModifyProduct(ActionEvent event) {

    }

    @FXML
    void actionAddProduct(ActionEvent event) {

    }

    @FXML
    void actionExit(ActionEvent event) {
        Platform.exit();
    }

}

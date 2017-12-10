package main.view_controller;

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

    private Main mainApp;
    FilteredList<Part> filteredPartData;
    SortedList<Part> sortedPartData;
    FilteredList<Product> filteredProductData;
    SortedList<Product> sortedProductData;

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

        initPartFilter();
        initProductFilter();
    }

    @FXML
    void handleDeletePart() {
        int selectedIndex = tableviewPart.getSelectionModel().getSelectedIndex();
        tableviewPart.getItems().remove(selectedIndex);
    }

    @FXML
    void handleModifyPart() {
        Part selectedPart = tableviewPart.getSelectionModel().getSelectedItem();
        mainApp.showAddPart(selectedPart);
    }

    @FXML
    void handleAddPart() {
        Part newPart = new InhousePart();
        mainApp.showAddPart(newPart);
    }

    @FXML
    void handleDeleteProduct() {
        int selectedIndex = tableviewProduct.getSelectionModel().getSelectedIndex();
        tableviewProduct.getItems().remove(selectedIndex);
    }

    @FXML
    void handleModifyProduct() {

    }

    @FXML
    void handleAddProduct() {
        mainApp.showAddProduct();
    }

    @FXML
    void handleExit() {
        Platform.exit();
    }

    private void initPartFilter() {
        filteredPartData = new FilteredList<>(mainApp.getInventory().getAllParts(), p -> true);

        textfieldPart.textProperty().addListener((observable, oldValue, newValue) -> filteredPartData.setPredicate(part -> {

            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true;

            } else if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            if (isDouble(lowerCaseFilter) || lowerCaseFilter.equals(".")) { //If our search string is a double or if it is a "." character indicating a decimal
                if (Double.toString(part.getPrice()).contains(lowerCaseFilter)) { //If our price value contains the searched number
                    return true;
                }

                if (isInteger(lowerCaseFilter)) { //If our search string is an integer
                    if (Integer.toString(part.getPartID()).contains(lowerCaseFilter)) { //If our productID value contains the searched numbers
                        return true;
                    }

                    if (Integer.toString(part.getInStock()).contains(lowerCaseFilter)) { //If our inStock value contains the searched numbers
                        return true;
                    }
                }
            }

            return false;
        }));

        sortedPartData = new SortedList<>(filteredPartData);
        sortedPartData.comparatorProperty().bind(tableviewPart.comparatorProperty());
        tableviewPart.setItems(sortedPartData);
    }

    private void initProductFilter() {
        filteredProductData = new FilteredList<>(mainApp.getInventory().getProducts(), p -> true);

        textfieldProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProductData.setPredicate(product -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (product.getName().toLowerCase().contains(lowerCaseFilter)) { //If the product name contains the search String
                    return true;
                } else if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                if (isDouble(lowerCaseFilter) || lowerCaseFilter.equals(".")) { //If our search string is a double or if it is a "." character indicating a decimal
                    if (Double.toString(product.getPrice()).contains(lowerCaseFilter)) { //If our price value contains the searched number
                        return true;
                    }

                    if (isInteger(lowerCaseFilter)) { //If our search string is an integer
                        if (Integer.toString(product.getProductID()).contains(lowerCaseFilter)) { //If our productID value contains the searched numbers
                            return true;
                        }

                        if (Integer.toString(product.getInStock()).contains(lowerCaseFilter)) { //If our inStock value contains the searched numbers
                            return true;
                        }
                    }
                }
                
                return false;
            });
        });

        sortedProductData = new SortedList<>(filteredProductData);
        sortedProductData.comparatorProperty().bind(tableviewProduct.comparatorProperty());
        tableviewProduct.setItems(sortedProductData);
    }

    public boolean isInteger(String s) {
        try {
            int num = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDouble(String s) {
        try {
            double num = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

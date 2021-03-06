package main.view_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.model.Inventory;
import main.model.Part;
import main.model.Product;

import java.util.Optional;

import static main.util.NumberUtil.isDouble;
import static main.util.NumberUtil.isInteger;

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

    private FilteredList<Part> filteredPartData;
    private ObservableList<Part> productParts;

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
        productParts = FXCollections.observableList(product.getAssociatedParts());
        tableviewProductPart.setItems(productParts);

        initPartFilter();
    }

    @FXML
    void handleAddPart() {
        Part selectedPart = tableviewAddPart.getSelectionModel().getSelectedItem();
        productParts.add(selectedPart);
    }

    @FXML
    void handleDeletePart() {
        if (errorBeforeDelete()) {
            return;
        }

        Part selectedPart = tableviewProductPart.getSelectionModel().getSelectedItem();
        productParts.remove(findPartIndex(selectedPart));
    }

    @FXML
    void handleProductSave() {
        if (errorBeforeSave()) {
            return;
        }

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
    void handleProductCancel() {
        if (errorBeforeCancel()) {
            return;
        }

        dialogStage.close();
    }

    private boolean isNewProduct() {
        return product.getProductID() == inventory.getProductCount();
    }

    //Enables part searching
    private void initPartFilter() {
        filteredPartData = new FilteredList<>(inventory.getAllParts(), p -> true);

        textfieldSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredPartData.setPredicate(part -> {

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

        SortedList<Part> sortedPartData = new SortedList<>(filteredPartData);
        sortedPartData.comparatorProperty().bind(tableviewAddPart.comparatorProperty());
        tableviewAddPart.setItems(sortedPartData);
    }

    private int findPartIndex(Part part) {
        //Returns the index of a part in associatedParts.
        for (int i = 0; i < productParts.size(); i++) {
            if (productParts.get(i).getPartID() == part.getPartID()) {
                return i;
            }
        }

        return -1;
    }

    private boolean errorBeforeSave() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Saving Part");
        alert.setHeaderText(null);

        //Display alert for incorrect inputs
        if (textfieldProductPrice.getText().equals("")) {
            alert.setContentText("Name cannot be empty");
            alert.showAndWait();
            return true;
        }

        if (!isInteger(textfieldProductInv.getText())) {
            alert.setContentText("Inv must be an integer");
            alert.showAndWait();
            return true;
        }

        if (!isDouble(textfieldProductPrice.getText())) {
            alert.setContentText("Price/Cost must be a number");
            alert.showAndWait();
            return true;
        }

        if (!isInteger(textfieldProductMin.getText())) {
            alert.setContentText("Min must be an integer");
            alert.showAndWait();
            return true;
        }

        if (!isInteger(textfieldProductMax.getText())) {
            alert.setContentText("Max must be an integer");
            alert.showAndWait();
            return true;
        }

        int inv = Integer.parseInt(textfieldProductInv.getText());
        int min = Integer.parseInt(textfieldProductMin.getText());
        int max = Integer.parseInt(textfieldProductMax.getText());

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


        /*TODO
         * Due to project specifications, the requirements below must be met:
         *
         * 1. "ensuring that a product must always have at least one part"
         * 2. "preventing the user from deleting a product that has a part assigned to it"
         *
         * Because of these, the user will not be able to delete a product because the user will never be able to save a
         * product without any parts, but the user will not be able to delete a product because it must always have a part.
         * */
        if (product.getAssociatedParts().size() == 0) { // If product has no parts
            alert.setContentText("A product must have at least one part");
            alert.showAndWait();
            return true;
        }

        double productPriceTotal = 0.0;
        for (Part part : product.getAssociatedParts()) { //Add the total price of parts in a product
            productPriceTotal += part.getPrice();
        }

        if (productPriceTotal > Double.parseDouble(textfieldProductPrice.getText())) { //If the total price of parts is more than the product itself
            alert.setContentText("The price of a product cannot be less than the cost of parts");
            alert.showAndWait();
            return true;
        }

        return false;
    }

    private boolean errorBeforeDelete() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Deleting Part");
        alert.setHeaderText(null);

        /*TODO
         * Due to project specifications, the requirements below must be met:
         *
         * 1. "ensuring that a product must always have at least one part"
         * 2. "preventing the user from deleting a product that has a part assigned to it"
         *
         * Because of these, the user will not be able to delete a product because the user will never be able to save a
         * product without any parts, but the user will not be able to delete a product because it must always have a part.
         * */
        if (product.getAssociatedParts().size() > 0) {
            alert.setContentText("A product cannot be deleted before removing all of its parts");
            alert.showAndWait();
            return true;
        }


        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Dialog");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() != ButtonType.OK){ //If our user presses cancel
            return true;
        }

        return false;
    }

    private boolean errorBeforeCancel() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Dialog");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() != ButtonType.OK){ //If our user presses cancel
            return true;
        }

        return false;
    }
}

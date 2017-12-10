package main.view_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.Inventory;
import main.model.Part;
import main.model.Product;

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
        Part selectedPart = tableviewProductPart.getSelectionModel().getSelectedItem();
        productParts.remove(findPartIndex(selectedPart));
    }

    @FXML
    void handleProductSave() {
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

    private boolean isInteger(String s) {
        try {
            int num = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String s) {
        try {
            double num = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
}

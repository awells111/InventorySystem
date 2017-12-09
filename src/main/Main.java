package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.InhousePart;
import main.model.OutsourcedPart;
import main.model.Part;
import main.model.Product;
import main.view_controller.AddPartController;
import main.view_controller.AddProductController;
import main.view_controller.MainScreenController;

import java.io.IOException;
import java.util.ArrayList;

import static main.view_controller.AddPartController.FXML_ADD_PART;
import static main.view_controller.AddProductController.FXML_ADD_PRODUCT;
import static main.view_controller.MainScreenController.FXML_MAIN_SCREEN;

public class Main extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    private ObservableList<Part> partData = FXCollections.observableArrayList();
    private ObservableList<Product> productData = FXCollections.observableArrayList();

    public ObservableList<Part> getPartData() {
        return partData;
    }

    public ObservableList<Product> getProductData() {
        return productData;
    }

    private void addSampleData() {
        //todo delete in final app version
        partData.add(new InhousePart(1, "A", 1.0, 5, 1, 5, 10));
        partData.add(new OutsourcedPart(2, "B", 1.0, 5, 1, 5, "Company"));
        partData.add(new InhousePart(3, "C", 1.0, 5, 1, 5, 10));
        partData.add(new OutsourcedPart(4, "D", 1.0, 5, 1, 5, "Company"));
        partData.add(new InhousePart(5, "E", 1.0, 5, 1, 5, 10));
        partData.add(new OutsourcedPart(6, "F", 1.0, 5, 1, 5, "Company"));
        partData.add(new InhousePart(7, "G", 1.0, 5, 1, 5, 10));
        partData.add(new OutsourcedPart(8, "H", 1.0, 5, 1, 5, "Company"));

        ArrayList<Part> sampleParts = new ArrayList<>();
        sampleParts.add(partData.get(0));
        sampleParts.add(partData.get(1));
        sampleParts.add(partData.get(2));
        sampleParts.add(partData.get(3));

        productData.add(new Product(sampleParts, 1, "AA", 1.5, 1, 5, 10));
        productData.add(new Product(sampleParts, 1, "AB", 1.5, 1, 5, 10));
        productData.add(new Product(sampleParts, 1, "AC", 1.5, 1, 5, 10));
        productData.add(new Product(sampleParts, 1, "AD", 1.5, 1, 5, 10));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //todo use getters
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inventory System");
//        Parent root = FXMLLoader.load(getClass().getResource(FXML_MAIN_SCREEN));
//        this.primaryStage.setScene(new Scene(root, 800, 600));
//        this.primaryStage.show();

        initRootLayout();

        addSampleData();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(FXML_MAIN_SCREEN));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            MainScreenController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public boolean showAddPart(Part part) {

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(FXML_ADD_PART));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Part");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AddPartController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPart(part);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showAddProduct() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(FXML_ADD_PRODUCT));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AddProductController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

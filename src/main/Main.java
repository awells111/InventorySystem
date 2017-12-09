package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.*;
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

    private Inventory inventory = new Inventory();

    private void addSampleData() {
        //todo delete in final app version
        inventory.addPart(new InhousePart("A", 1.0, 5, 1, 5, 10));
        inventory.addPart(new OutsourcedPart("B", 1.0, 5, 1, 5, "Company"));
        inventory.addPart(new InhousePart("C", 1.0, 5, 1, 5, 10));
        inventory.addPart(new OutsourcedPart("D", 1.0, 5, 1, 5, "Company"));
        inventory.addPart(new InhousePart("E", 1.0, 5, 1, 5, 10));
        inventory.addPart(new OutsourcedPart("F", 1.0, 5, 1, 5, "Company"));
        inventory.addPart(new InhousePart("G", 1.0, 5, 1, 5, 10));
        inventory.addPart(new OutsourcedPart("H", 1.0, 5, 1, 5, "Company"));

        ArrayList<Part> sampleParts = new ArrayList<>();
        sampleParts.add(inventory.lookupPart(1));
        sampleParts.add(inventory.lookupPart(2));
        sampleParts.add(inventory.lookupPart(3));
        sampleParts.add(inventory.lookupPart(4));

        inventory.addProduct(new Product(sampleParts, "AA", 1.5, 1, 5, 10));
        inventory.addProduct(new Product(sampleParts, "AB", 1.5, 1, 5, 10));
        inventory.addProduct(new Product(sampleParts, "AC", 1.5, 1, 5, 10));
        inventory.addProduct(new Product(sampleParts, "AD", 1.5, 1, 5, 10));
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
            controller.setPart(inventory, part);

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

    public Inventory getInventory() {
        return inventory;
    }
}

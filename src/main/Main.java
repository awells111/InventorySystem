package main;

import javafx.application.Application;
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
        this.primaryStage = primaryStage;
        getPrimaryStage().setTitle("Inventory System");

        initRootLayout();

        addSampleData();
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(FXML_MAIN_SCREEN));
            AnchorPane rootLayout = loader.load();

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

    private Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showAddPart(Part part) {

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

        } catch (IOException e) {
            e.printStackTrace();
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

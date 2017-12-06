package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo initialize
    }

    public void actionSearchPart() {
        System.out.println("searchPartClicked");
    }

    public void actionDeletePart() {
        System.out.println("deletePartClicked");
    }

    public void actionModifyPart() {
        System.out.println("modifyPartClicked");
    }

    public void actionAddPart() {
        System.out.println("addPartClicked");
    }

    public void actionSearchProduct() {
        System.out.println("searchProductClicked");
    }

    public void actionDeleteProduct() {
        System.out.println("deleteProductClicked");
    }

    public void actionModifyProduct() {
        System.out.println("modifyProductClicked");
    }

    public void actionAddProduct() {
        System.out.println("addProductClicked");
    }

    public void actionExit() {
        System.out.println("exitClicked");
    }
}

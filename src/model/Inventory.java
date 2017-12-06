package model;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Product> products;
    ArrayList<Part> allParts;

    public Inventory(ArrayList<Product> products, ArrayList<Part> allParts) {
        this.products = products;
        this.allParts = allParts;
    }

    //todo addProduct(product) : void
    //todo removeProduct(int) : boolean
    //todo lookupProduct(int) : product
    //todo updateProduct(int) : void
    //todo addPart(part) : void
    //todo deletePart(part) : boolean
    //todo lookupPart(int) : part
    //todo updatePart(int) : void
}

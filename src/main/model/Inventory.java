package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Product> products = FXCollections.observableArrayList();
    private ObservableList<Part> allParts = FXCollections.observableArrayList();

    private int partCount = 1;
    private int productCount = 1;

    public Inventory() {

    }

    public void addProduct(Product product) {
        product.setProductID(productCount);
        products.add(product);
        productCount++;
    }

    private int findProductIndex(int productID) {
        //Returns the index of a product in products.
        for (int i = 0; i < getProducts().size(); i++) {
            if (getProducts().get(i).getProductID() == productID) {
                return i;
            }
        }

        return -1;
    }

    public boolean removeProduct(Product product) {
        return removeProduct(product.getProductID());
    }

    public boolean removeProduct(int productID) {
        getProducts().remove(findProductIndex(productID));
        return true;
    }

    public Product lookupProduct(int productID) {
        return getProducts().get(findProductIndex(productID));
    }

    public void updateProduct(Product newProduct) {
        //Replaces the old product with a new one. This is only possible because the productID stays the same.
        getProducts().set(findProductIndex(newProduct.getProductID()), newProduct);
    }

    private int findPartIndex(int partID) {
        //Returns the index of a part in allParts.
        for (int i = 0; i < getAllParts().size(); i++) {
            if (getAllParts().get(i).getPartID() == partID) {
                return i;
            }
        }

        return -1;
    }

    public void addPart(Part part) {
        part.setPartID(partCount);
        getAllParts().add(part);
        partCount++;
    }

    public boolean deletePart(Part part) {
        return getAllParts().remove(part);
    }

    public Part lookupPart(int partID) {
        return getAllParts().get(findPartIndex(partID));
    }

    public void updatePart(Part newPart) {
        //Replaces the old part with a new one. This is only possible because the partID stays the same.
        getAllParts().set(findPartIndex(newPart.getPartID()), newPart);
    }

    //Getters and Setters
    public ObservableList<Product> getProducts() {
        return products;
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public int getPartCount() {
        return partCount;
    }

    public int getProductCount() {
        return productCount;
    }
}

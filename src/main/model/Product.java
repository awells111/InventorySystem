package main.model;

import javafx.beans.property.*;

import java.util.ArrayList;

public class Product {

    private ArrayList<Part> associatedParts;
    private final IntegerProperty productID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    public Product() {
        this("", 0, 0, 0, 0);
    }

    public Product(String name, double price, int inStock, int min, int max) {
        this(-1, name, price, inStock, min, max);
    }

    private Product(int productID, String name, double price, int inStock, int min, int max) {
        associatedParts = new ArrayList<>();
        this.productID = new SimpleIntegerProperty(productID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    public void addAssociatedPart(Part part) {
        getAssociatedParts().add(part);
    }

    private int findPartIndex(int partID) {
        //Returns the index of a part in associatedParts.
        for (int i = 0; i < getAssociatedParts().size(); i++) {
            if (getAssociatedParts().get(i).getPartID() == partID) {
                return i;
            }
        }

        return -1;
    }

    public boolean removeAssociatedPart(Part part) {
        return removeAssociatedPart(part.getPartID());
    }

    public boolean removeAssociatedPart(int partID) {
        getAssociatedParts().remove(findPartIndex(partID));
        return true;
    }

    public Part lookupAssociatedPart(int partID) {
        return getAssociatedParts().get(findPartIndex(partID));
    }


    //Getters and Setters
    public ArrayList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ArrayList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public int getProductID() {
        return productID.get();
    }

    public IntegerProperty productIDProperty() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty productNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty productPriceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getInStock() {
        return inStock.get();
    }

    public IntegerProperty productInStockProperty() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public int getMin() {
        return min.get();
    }

    public IntegerProperty productMinProperty() {
        return min;
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty productMaxProperty() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }
}

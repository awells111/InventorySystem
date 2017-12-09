package main.model;

import javafx.beans.property.*;

public abstract class Part {

    private final IntegerProperty partID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    Part(int partID, String name, double price, int inStock, int min, int max) {
        this.partID = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    //Getters and Setters
    public int getPartID() {
        return partID.get();
    }

    public IntegerProperty partIDProperty() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID.set(partID);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty partNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty partPriceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getInStock() {
        return inStock.get();
    }

    public IntegerProperty partInStockProperty() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public int getMin() {
        return min.get();
    }

    public IntegerProperty partMinProperty() {
        return min;
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty partMaxProperty() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }
}

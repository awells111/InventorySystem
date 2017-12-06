package model;

import java.util.ArrayList;

class Product {

    private ArrayList<Part> associatedParts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    public Product(ArrayList<Part> associatedParts, int productID, String name, double price, int inStock, int min, int max) {
        this.associatedParts = associatedParts;
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
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
    private ArrayList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ArrayList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}

package model;

import java.util.ArrayList;

public class Product {

    private ArrayList<Part> associatedParts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    private static final int ERROR_CODE_NO_PART = -1;

    public Product(ArrayList<Part> associatedParts, int productID, String name, double price, int inStock, int min, int max) {
        this.associatedParts = associatedParts;
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }

    //todo Do not allow parts with the same ID
    //todo Do not allow negative numbers
    public void addAssociatedPart(Part part) {
        getAssociatedParts().add(part);
    }

    private int findPartIndex(int partID) {
        //Returns the index of a part in associatedParts. If that part doesn't exist, return ERROR_CODE_NO_PART.
        for (int i = 0; i < getAssociatedParts().size(); i++) {
            if (getAssociatedParts().get(i).getPartID() == partID) {
                return i;
            }
        }

        return ERROR_CODE_NO_PART;
    }

    private boolean isValidPart(int index) {
        if (index != ERROR_CODE_NO_PART) {
            return true;
        }

        return false;
    }

    public boolean removeAssociatedPart(Part part) {
        return removeAssociatedPart(part.getPartID());
    }

    public boolean removeAssociatedPart(int partID) {
        //Returns true if part is removed, false if no part is found or removed.
        int index = findPartIndex(partID);
        if (isValidPart(index)) {
            getAssociatedParts().remove(index);
            return true;
        }

        return false;
    }

    public Part lookupAssociatedPart(int partID) {
        int index = findPartIndex(partID);
        if (isValidPart(index)) {
            return getAssociatedParts().get(index);
        }

        //todo Handle invalid partID error
        return null;
    }

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

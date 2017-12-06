package model;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> products;
    private ArrayList<Part> allParts;

    public Inventory(ArrayList<Product> products, ArrayList<Part> allParts) {
        this.products = products;
        this.allParts = allParts;
    }

    public void addProduct(Product product) {
        products.add(product);
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

    //todo updateProduct(int) : void -- doesn't make sense to have int as only input

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
        getAllParts().add(part);
    }

    public boolean deletePart(Part part) {
        return getAllParts().remove(part);
    }

    public Part lookupPart(int partID) {
        return getAllParts().get(findPartIndex(partID));
    }

    //todo updatePart(int) : void -- doesn't make sense to have int as only input

    //Getters and Setters
    private ArrayList<Product> getProducts() {
        return products;
    }

    private void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    private ArrayList<Part> getAllParts() {
        return allParts;
    }

    private void setAllParts(ArrayList<Part> allParts) {
        this.allParts = allParts;
    }
}

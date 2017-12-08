package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OutsourcedPart extends Part {

    private final StringProperty companyName;

    public OutsourcedPart(int partID, String name, double price, int inStock, int min, int max, String companyName) {
        super(partID, name, price, inStock, min, max);
        this.companyName = new SimpleStringProperty(companyName);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
}

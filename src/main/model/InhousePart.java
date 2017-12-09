package main.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InhousePart extends Part {

    private final IntegerProperty machineID;

    public InhousePart() {
        this(0, null, 0.0, 0, 0, 0, 0);
    }

    public InhousePart(int partID, String name, double price, int inStock, int min, int max, int machineID) {
        super(partID, name, price, inStock, min, max);
        this.machineID = new SimpleIntegerProperty(machineID);
    }

    public IntegerProperty machineIDProperty() {
        return machineID;
    }

    public int getMachineID() {
        return machineID.get();
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
}

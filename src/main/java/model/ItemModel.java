package model;

import java.util.List;

// ItemModel.java
public class ItemModel {
    private int id;
    private String name;
    private String detail;
    private int unit;

    public ItemModel(){}
    // Constructors, getters, setters, and other methods
    public ItemModel(int id, String name, String detail, int unit) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScannedItemModel {
    ItemModel item;
    double amount;

    @JsonCreator
    public ScannedItemModel(@JsonProperty("item") ItemModel item, @JsonProperty("amount") double amount) {
        this.item = item;
        this.amount = amount;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



}

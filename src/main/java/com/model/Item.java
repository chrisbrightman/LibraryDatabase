package com.model;

public class Item {
    private static final String ADD_COMMAND = "INSERT INTO item (";
    private String item_name;
    private String description;

    public Item(String item_name, String description) {
        if (item_name.length() < 10) {
            this.item_name = item_name;
        }
        if (description.length() < 100) {
            this.description = description;
        }
    }

    public String getAddCommand() {
        return null;
    }
}

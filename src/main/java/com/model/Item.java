package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Item {
    private static final String ADD_COMMAND = "INSERT INTO item (i_name, l_name, " +
            "description, l_rank, age, last_day) VALUES ('";
    private static final String DELETE_COMMAND = "DELETE FROM item WHERE i_name=";
    private String item_name = null;
    private String description = null;
    private String list_name = null;
    private int list_rank = 0;
    private int age = 0;
    private String last_day = null;

    public Item(String item_name, String description) {
        if (item_name.length() < 10) {
            this.item_name = item_name;
        }
        if (description.length() < 100) {
            this.description = description;
        }
    }

    public Item(ResultSet set) {
        item_name = getString("i_name", set);
        list_name = getString("l_name", set);
        description = getString("description", set);
        age = getInt("age", set);
        last_day = getString("last_day", set);
        list_rank = getInt("l_rank", set);
        if (item_name == null && list_name == null && description == null &&
                last_day == null && list_rank == 0 && age == 0) {
            System.err.println("ERROR: a result was not an item");
        }
    }

    public String getAddCommand() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime time = LocalDateTime.now();
        last_day = time.format(formatter);
        return ADD_COMMAND + item_name + "', NULL, '"
                + description + "', NULL, 0, '" + last_day +
                "');";
    }

    public String getDeleteCommand() {
        return DELETE_COMMAND + "'" + item_name + "';";
    }

    private static int getInt(String columnName, ResultSet set) {
        try {
            return set.getInt(set.findColumn(columnName));
        }
        catch (SQLException ex) {
            return 0;
        }
    }

    private static String getString(String columnName, ResultSet set) {
        try {
            return set.getString(set.findColumn(columnName));
        }
        catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item other = (Item) obj;
            return this.item_name.equals(other.item_name) &&
                    this.list_name.equals(other.list_name) &&
                    this.description.equals(other.description) &&
                    this.last_day.equals(other.last_day) &&
                    this.list_rank == other.list_rank &&
                    this.age == other.age;
        }
        return false;
    }

    @Override
    public String toString() {
        return "{ name: '" + item_name + "', description: '" + description + "', list_name: '"
                + list_name + "', list_rank: " + list_rank + ", age: "
                + age + ", last_day: " + last_day + " }";
    }
}

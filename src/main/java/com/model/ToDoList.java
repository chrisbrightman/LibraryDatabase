package com.model;

public class ToDoList {

    private static final String ADD_COMMAND = "INSERT INTO list (l_name, max_size, description, last_day) " +
            "VALUES ";

    private String list_name = null;
    private int max_size = 0;
    private String description = null;
    private String last_day = null;

    public ToDoList(String list_name, String description) {
        this.list_name = list_name;
        this.description = description;
    }

    public String getAddCommand() {
        return ADD_COMMAND + "('" + list_name + "', " + max_size +
                ", '" + description + "', '" +  last_day + "');";
    }

    @Override
    public String toString() {
        return "{ name: '" + list_name + "', description: '" + description
                + "', max_size: " + max_size + ", last_day: " + last_day + " }";
    }

}

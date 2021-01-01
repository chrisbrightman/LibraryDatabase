package com.control;

import com.model.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddItem {
    private Connection connection;

    public AddItem(Connection connection) {
        this.connection = connection;
    }

    public void addItem(String name, String description) {
        Item item = new Item(name, description);
        try {
            Statement statement = connection.createStatement();
            statement.execute(item.getAddCommand());
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

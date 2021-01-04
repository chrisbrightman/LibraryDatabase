package com.control;

import com.model.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddItem {
    private final Connection connection;

    public AddItem(Connection connection) {
        this.connection = connection;
    }

    public void addItem(Item item) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(item.getAddCommand());
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

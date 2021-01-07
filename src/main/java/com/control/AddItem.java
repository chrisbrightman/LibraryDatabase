package com.control;

import com.model.Item;
import com.ui.App;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddItem implements CommandDispatch {
    private final Connection connection;

    public AddItem(Connection connection) {
        this.connection = connection;
    }

    private void addItem(Item item) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(item.getAddCommand());
            if (App.DEBUG) {
                System.out.println("DEBUG: item " + item + " inserted into the database");
            }
        }
        catch (SQLException ex) {
            System.err.println("error executing command: " + item.getAddCommand());
            ex.printStackTrace();
        }
    }

    @Override
    public void assignArgs(List<String> args) {
        addItem(new Item(args.get(0), args.get(1)));
    }
}

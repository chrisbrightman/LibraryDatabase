package com.control;

import com.model.Item;
import com.ui.App;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DeleteItem implements CommandDispatch {

    private Connection connection;

    private static final String SELECT_ITEM = "SELECT * from item" +
            " WHERE i_name=";

    public DeleteItem(Connection connection) {
        this.connection = connection;
    }

    private void deleteItem(Item item) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(item.getDeleteCommand());
            if (App.DEBUG) {
                System.out.println("DEBUG: item " + item + " deleted from database");
            }
        }
        catch (SQLException ex) {
            System.err.println("error executing command: " + item.getDeleteCommand());
            ex.printStackTrace();
        }
    }

    @Override
    public void assignArgs(List<String> args) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SELECT_ITEM + "'" + args.get(0) + "';");
            this.deleteItem(new Item(result));
        }
        catch (SQLException ex) {
            System.err.println("ERROR: item:'" + args.get(0) + "' does not exists");
        }
    }
}

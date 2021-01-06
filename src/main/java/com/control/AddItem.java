package com.control;

import com.model.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddItem implements CommandDispatch {
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

    @Override
    public void assignArgs(List<String> args) {
        addItem(new Item(args.get(0), args.get(1)));
    }
}

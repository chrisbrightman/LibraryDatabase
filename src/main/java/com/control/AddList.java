package com.control;

import com.model.ToDoList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddList {

    private Connection connection;

    public AddList(Connection connection) {
        this.connection = connection;
    }

    public void addList(ToDoList list) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(list.getAddCommand());
        } catch (SQLException ex) {

        }
    }

}

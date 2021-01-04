package com.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.sql.*;

public class App extends Application {

    private static Connection connection = null;

    public static final String dbFile = "jdbc:sqlite:data/library.db";

    static boolean DEBUG = false;

    public void start(  Stage primaryStage) {
        StackPane root = new StackPane(new Label("Hello World!"));

        Scene scene = new Scene(root, 800, 600);



        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException("Should not happen!");
            }
            System.exit(0);
        }).start();
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("true")) {
            DEBUG = true;
        }
        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                if (!dataDir.mkdir()) {
                    System.err.println("Could not make data directory");
                    System.exit(1);
                }
            }
            File dataFile = new File("data/library.db");
            connection = DriverManager.getConnection(dbFile);
            if (connection != null && DEBUG) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("DEBUG: A " + metaData.getDriverName() +
                        " database was connected to at " + dbFile);
            }
            if (DEBUG) {
                System.out.println("DEBUG: A new library.db file was created");
                System.out.println("DEBUG: Creating data tables");
            }
            final String createItem = "CREATE TABLE IF NOT EXISTS item (" +
                    "i_name TEXT NOT NULL PRIMARY KEY," +
                    "description TEXT NOT NULL," +
                    "l_rank INTEGER," +
                    "age INTEGER," +
                    "last_day TEXT," +
                    "l_name TEXT," +
                    "FOREIGN KEY(l_name) REFERENCES list(l_name)" +
                    ");";
            final String createList = "CREATE TABLE IF NOT EXISTS list (" +
                    "l_name TEXT NOT NULL PRIMARY KEY," +
                    "max_size INTEGER," +
                    "description TEXT NOT NULL," +
                    "last_day TEXT" +
                    ");";
            assert connection != null;
            Statement statement = connection.createStatement();
            statement.execute("PRAGMA foreign_keys = ON;");
            statement.execute(createList);
            if (DEBUG) {
                System.out.println("DEBUG: Created list table");
            }
            ResultSet resultSet = statement.executeQuery("SELECT * FROM list");
            statement.execute(createItem);
            if (DEBUG) {
                System.out.println("DEBUG: Create item table");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(ex.getErrorCode());
        }
        launch(args);
    }
}

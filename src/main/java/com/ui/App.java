package com.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.sql.*;

public class App extends Application {

    private static Connection connection = null;

    private SceneMap sceneMap;

    public static float width;
    public static float height;

    public static final String dbFile = "jdbc:sqlite:data/library.db";

    public static boolean DEBUG = false;

    /**
     * make the main scene
     * @param primaryStage the stage
     */
    public void start(  Stage primaryStage) {
        sceneMap = new SceneMap(primaryStage);

        VBox mainBox = new VBox(10);
        mainBox.setPrefHeight(height - 100);
        mainBox.setMinHeight(height - 150);
        mainBox.setPrefWidth(height - 100);
        mainBox.setMinWidth(height - 150);
        SplitPane buttons = new SplitPane();
        buttons.getItems().addAll(new AddButton(sceneMap), new ListButton(sceneMap));

        mainBox.getChildren().addAll(new Label("Hello World"), buttons);


        Scene scene = new Scene(mainBox, width, height);
        sceneMap.addScene("main", scene);



        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException("Should not happen!");
            }
            System.exit(0);
        }).start();
    }

    public static void main(String[] args) {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = (float) device.getDisplayMode().getWidth() / 2;
        height = (float) ((float) device.getDisplayMode().getHeight() / 1.7);
        if (args.length > 0 && args[0].equalsIgnoreCase("true")) {
            DEBUG = true;
        }
        // establishing database connection
        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                if (!dataDir.mkdir()) {
                    System.err.println("Could not make data directory");
                    System.exit(1);
                }
            }
            // main db file
            File dataFile = new File("data/library.db");
            connection = DriverManager.getConnection(dbFile);
            // TODO: fix this at some point
            assert connection != null;
            if (DEBUG) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("DEBUG: A " + metaData.getDriverName() +
                        " database was connected to at " + dbFile);
            }
            if (DEBUG) {
                System.out.println("DEBUG: A new library.db file was created");
                System.out.println("DEBUG: Creating data tables");
            }
            // SQL Command to create an item if not already made
            final String createItem = "CREATE TABLE IF NOT EXISTS item (" +
                    "i_name TEXT NOT NULL PRIMARY KEY," +
                    "description TEXT NOT NULL," +
                    "l_rank INTEGER," +
                    "age INTEGER," +
                    "last_day TEXT," +
                    "l_name TEXT," +
                    "FOREIGN KEY(l_name) REFERENCES list(l_name)" +
                    ");";
            // SQL Command to create a list if not already made
            final String createList = "CREATE TABLE IF NOT EXISTS list (" +
                    "l_name TEXT NOT NULL PRIMARY KEY," +
                    "max_size INTEGER," +
                    "description TEXT NOT NULL," +
                    "last_day TEXT" +
                    ");";
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

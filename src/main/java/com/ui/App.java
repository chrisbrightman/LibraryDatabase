package com.ui;

import com.control.AddItem;
import com.control.DeleteItem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.sql.*;

public class App extends Application {

    private static Connection connection = null;

    private SceneMap sceneMap;
    private static final GraphicsDevice device
            = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public static final float width = (float) device.getDisplayMode().getWidth() / 2;
    public static final float height = (float) ((float) device.getDisplayMode().getHeight() / 1.7);

    public static final String dbFile = "jdbc:sqlite:data/library.db";

    // SQL Command to create an item if not already made
    private static final String createItem = "CREATE TABLE IF NOT EXISTS item (" +
            "i_name TEXT NOT NULL PRIMARY KEY," +
            "description TEXT NOT NULL," +
            "l_rank INTEGER," +
            "age INTEGER," +
            "last_day TEXT," +
            "l_name TEXT," +
            "FOREIGN KEY(l_name) REFERENCES list(l_name)" +
            ");";

    // SQL Command to create a list if not already made
    private static final String createList = "CREATE TABLE IF NOT EXISTS list (" +
            "l_name TEXT NOT NULL PRIMARY KEY," +
            "max_size INTEGER," +
            "description TEXT NOT NULL," +
            "last_day TEXT" +
            ");";

    public static boolean DEBUG;

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
        AddItem adder = new AddItem(connection);
        DeleteItem deleter = new DeleteItem(connection);
        buttons.getItems().addAll(new AddButton(sceneMap, adder), new DeleteButton(sceneMap, deleter));

        mainBox.getChildren().addAll(new Label("Hello World"), buttons);


        Scene scene = new Scene(mainBox, width, height);
        sceneMap.addScene("main", scene);



        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DEBUG = args.length > 0 && args[0].equalsIgnoreCase("true");
        // establishing database connection
        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                if (!dataDir.mkdir()) {
                    System.err.println("Could not make data directory");
                    System.exit(1);
                }
            }
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
            Statement statement = connection.createStatement();
            statement.execute("PRAGMA foreign_keys = ON;");
            statement.execute(createList);
            if (DEBUG) {
                System.out.println("DEBUG: Created list table");
            }
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

package com.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.text.Style;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App extends Application {

    private static Connection connection = null;

    private static final String dbFile = "jdbc:sqlite:data/library.db";

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
            connection = DriverManager.getConnection(dbFile);
            if (connection != null && DEBUG) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("A " + metaData.getDriverName() + " database was initialized at " + dbFile);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(ex.getErrorCode());
        }
        System.out.println("Hello World!");
        launch(args);
    }
}

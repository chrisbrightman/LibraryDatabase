package com.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
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
        System.out.println("Hello World!");
        launch(args);
    }
}

package com.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class SceneMap {
    private final HashMap<String, Scene> scenes;
    private final Stage mainStage;

    public SceneMap (Stage mainStage) {
        this.mainStage = mainStage;
        scenes = new HashMap<>();
    }

    public void addScene(String label, Scene scene) {
        scenes.put(label, scene);
    }

    public boolean displayScene(String label) {
        if (scenes.containsKey(label)) {
            mainStage.setScene(scenes.get(label));
            mainStage.show();
            if (App.DEBUG) {
                System.out.println("DEBUG: scene changed to " + label);
            }
            return true;
        }
        if (App.DEBUG) {
            System.out.println("DEBUG: the scene " + label
                    + " does not exists in scene map");
        }
        return false;
    }

    public Scene getScene(String label) {
        return scenes.get(label);
    }

}

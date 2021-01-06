package com.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddButton extends LibraryButton {


    public AddButton(SceneMap sceneMap) {
        super(sceneMap, "add items");
        VBox vBox = new VBox(new Label("Name"),
                new TextField(),
                new Label("Description"),
                new TextField(),
                new SubmitButton(sceneMap, "add"));
        Scene addScene = new Scene(vBox, App.width, App.height);
        sceneMap.addScene("add", addScene);
    }

    @Override
    public void fire() {
        super.fire();
        sceneMap.displayScene("add");
    }


}

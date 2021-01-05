package com.ui;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddButton extends LibraryButton {


    public AddButton(SceneMap sceneMap) {
        super(sceneMap, "add items");
        VBox vBox = new VBox();
        // TODO: fix this text field labeling
        TextField nameField = new TextField("Name");
        TextField descriptionField = new TextField("Description");
        SubmitButton submitButton = new SubmitButton(sceneMap, "add");
        vBox.getChildren().addAll(nameField, descriptionField, submitButton);
        Scene addScene = new Scene(vBox, App.width, App.height);
        sceneMap.addScene("add", addScene);
    }

    @Override
    public void fire() {
        super.fire();
        sceneMap.displayScene("add");
    }


}

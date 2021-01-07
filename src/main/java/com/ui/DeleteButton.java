package com.ui;

import com.control.DeleteItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DeleteButton extends LibraryButton {

    DeleteItem deleter;

    SubmitFunc func = (scene, dispatch) -> {
        return null;
    };

    protected DeleteButton(SceneMap sceneMap, DeleteItem deleter) {
        super(sceneMap, "Delete Items");
        this.deleter = deleter;
        VBox vBox = new VBox(new Label("Name"),
                            new TextField(),
                            new SubmitButton(sceneMap, deleter, "delete", func));
    }
}

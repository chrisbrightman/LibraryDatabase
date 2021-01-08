package com.ui;

import com.control.DeleteItem;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class DeleteButton extends LibraryButton {

    DeleteItem deleter;

    SubmitFunc deleteItem = (scene, dispatch) -> {
        Parent root = scene.getRoot();
        ObservableList<Node> nodes = root.getChildrenUnmodifiable();
        TextField name = (TextField) nodes.get(1);
        CharSequence nameSequence = name.getCharacters();
        List<String> values = new ArrayList<>();
        values.add(nameSequence.toString());
        dispatch.assignArgs(values);
        name.clear();
        return values;
    };

    protected DeleteButton(SceneMap sceneMap, DeleteItem deleter) {
        super(sceneMap, "Delete Items");
        this.deleter = deleter;
        VBox vBox = new VBox(new Label("Name"),
                            new TextField(),
                            new SubmitButton(sceneMap, deleter, "delete", deleteItem),
                            new HomeButton(sceneMap));
        Scene delete = new Scene(vBox, App.width, App.height);
        sceneMap.addScene("delete", delete);
    }


    @Override
    public void fire() {
        super.fire();
        sceneMap.displayScene("delete");
    }
}

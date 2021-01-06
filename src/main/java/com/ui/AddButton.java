package com.ui;

import com.control.AddItem;
import com.control.CommandDispatch;
import com.model.Item;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class AddButton extends LibraryButton {

    SubmitFunc addItem = (scene, dispatch) -> {
        Parent root = scene.getRoot();
        ObservableList<Node> nodeList = root.getChildrenUnmodifiable();
        TextField name = (TextField) nodeList.get(1);
        TextField description = (TextField) nodeList.get(3);
        CharSequence nameSequence = name.getCharacters();
        CharSequence desSequence = description.getCharacters();
        List<String> values = new ArrayList<>();
        values.add(nameSequence.toString());
        values.add(desSequence.toString());
        dispatch.assignArgs(values);
        name.clear();
        description.clear();
        return values;
    };

    public AddButton(SceneMap sceneMap, AddItem adder) {
        super(sceneMap, "add items");
        VBox vBox = new VBox(new Label("Name"),
                new TextField(),
                new Label("Description"),
                new TextField(),
                new SubmitButton(sceneMap, adder, "add", addItem),
                new HomeButton(sceneMap));
        Scene addScene = new Scene(vBox, App.width, App.height);
        sceneMap.addScene("add", addScene);
    }

    @Override
    public void fire() {
        super.fire();
        sceneMap.displayScene("add");
    }


}

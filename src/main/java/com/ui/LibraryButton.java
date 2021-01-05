package com.ui;

import javafx.scene.control.Button;

public abstract class LibraryButton extends Button {

    protected SceneMap sceneMap;

    protected LibraryButton(SceneMap sceneMap, String label) {
        super(label);
        this.sceneMap = sceneMap;
    }

}

package com.ui;

public class ListButton extends LibraryButton {


    public ListButton(SceneMap sceneMap) {
        super(sceneMap, "manage lists");

    }

    @Override
    public void fire() {
        super.fire();
        sceneMap.displayScene("list");
    }


}

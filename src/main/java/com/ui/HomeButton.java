package com.ui;

public class HomeButton extends LibraryButton {

    protected HomeButton(SceneMap sceneMap) {
        super(sceneMap, "return Home");
    }

    @Override
    public void fire() {
        super.fire();
        sceneMap.displayScene("main");
    }
}

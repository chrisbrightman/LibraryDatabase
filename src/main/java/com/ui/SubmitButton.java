package com.ui;


public class SubmitButton extends LibraryButton {

    private String toRefresh;

    public SubmitButton(SceneMap sceneMap, String toRefresh) {
        super(sceneMap, "Submit");
        this.toRefresh = toRefresh;
    }


    @Override
    public void fire() {
        super.fire();
        sceneMap.displayScene(toRefresh);
    }
}

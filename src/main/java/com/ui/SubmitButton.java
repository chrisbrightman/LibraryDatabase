package com.ui;


import com.control.CommandDispatch;

public class SubmitButton extends LibraryButton {

    private final String toRefresh;
    private final CommandDispatch dispatcher;
    private final SubmitFunc func;


    public SubmitButton(SceneMap sceneMap, CommandDispatch dispatcher, String toRefresh, SubmitFunc func) {
        super(sceneMap, "Submit");
        this.toRefresh = toRefresh;
        this.dispatcher = dispatcher;
        this.func = func;
    }


    @Override
    public void fire() {
        super.fire();
        func.process(sceneMap.getScene(toRefresh), dispatcher);
        sceneMap.displayScene(toRefresh);
    }
}

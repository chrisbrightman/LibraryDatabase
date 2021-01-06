package com.ui;

import com.control.CommandDispatch;
import javafx.scene.Scene;

import java.util.List;

@FunctionalInterface
public interface SubmitFunc {
    List<String> process(Scene scene, CommandDispatch dispatch);
}

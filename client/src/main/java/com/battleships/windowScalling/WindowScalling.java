package com.battleships.windowScalling;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;

public class WindowScalling {

    void enableFor(final Scene scene, final Parent contentPane) {
        ScallingParams scallingParams = calculateScallingParams(scene, contentPane);

        SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, scallingParams, contentPane);
        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);
    }

    private ScallingParams calculateScallingParams(Scene scene, Parent contentPane) {
        final double initWidth = scene.getWidth();
        final double initHeight = scene.getHeight();
        final double ratio = initWidth / initHeight;
        return new ScallingParams(initWidth, initHeight, ratio);
    }
}

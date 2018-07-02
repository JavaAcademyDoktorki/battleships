package com.battleships.windowScalling;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class WindowScalling {

    public void enableFor(final Scene scene, final Parent contentPane) {
        ScalingParams scalingParams = calculateScallingParams(scene, contentPane);

        SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, scalingParams, contentPane);
        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);
    }

    private ScalingParams calculateScallingParams(Scene scene, Parent contentPane) {
        final double initWidth = scene.getWidth();
        final double initHeight = scene.getHeight();
        final double ratio = initWidth / initHeight;
        return new ScalingParams(initWidth, initHeight, ratio);
    }
}

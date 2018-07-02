package com.battleships.windowScalling;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;

class SceneSizeChangeListener implements ChangeListener<Number> {
    private final Scene scene;
    private final ScalingParams scalingParams;
    private final Parent contentPane;

    public SceneSizeChangeListener(Scene scene, ScalingParams scalingParams, Parent contentPane) {
        this.scene = scene;
        this.scalingParams = scalingParams;
        this.contentPane = contentPane;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        double scaleFactor = calculateScaleFactor(scene.getWidth(), scene.getHeight());
        if (scaleFactor >= 1) {
            Scale scale = new Scale(scaleFactor, scaleFactor);
            scale.setPivotX(0);
            scale.setPivotY(0);
            scene.getRoot().getTransforms().setAll(scale);
            contentPane.prefWidth(scene.getWidth() / scaleFactor);
            contentPane.prefHeight(scene.getHeight() / scaleFactor);
        } else {
            contentPane.prefWidth(Math.max(scalingParams.width, scene.getWidth()));
            contentPane.prefHeight(Math.max(scalingParams.height, scene.getHeight()));
        }
    }

    private double calculateScaleFactor(double newWidth, double newHeight) {
        return newWidth / newHeight > scalingParams.ratio
                ? newHeight / scalingParams.height
                : newWidth / scalingParams.width;
    }
}
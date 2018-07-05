package com.battleships.windowScalling;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;

class SceneSizeChangeListener implements ChangeListener<Number> {
    private final Scene scene;
    private final Scaling scaling;
    private final Parent contentPane;

    SceneSizeChangeListener(Scene scene, Scaling scaling, Parent contentPane) {
        this.scene = scene;
        this.scaling = scaling;
        this.contentPane = contentPane;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        double scaleFactor = scaling.calculateScaleFactor(scene.getWidth(), scene.getHeight());
        if (areBothDimensionsGreaterThanInitial(scaleFactor)) {
            Scale scale = new Scale(scaleFactor, scaleFactor);
            scene.getRoot().getTransforms().setAll(scale);
            contentPane.prefWidth(scene.getWidth() / scaleFactor);
            contentPane.prefHeight(scene.getHeight() / scaleFactor);
        } else {
            contentPane.prefWidth(Math.max(scaling.getInitialWidth(), scene.getWidth()));
            contentPane.prefHeight(Math.max(scaling.getInitialHeight(), scene.getHeight()));
        }
    }

    private boolean areBothDimensionsGreaterThanInitial(double scaleFactor) {
        return scaleFactor >= 1;
    }
}
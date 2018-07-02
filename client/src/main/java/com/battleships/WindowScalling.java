package com.battleships;

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

    private class SceneSizeChangeListener implements ChangeListener<Number> {
        private final Scene scene;
        private final ScallingParams scallingParams;
        private final Parent contentPane;

        public SceneSizeChangeListener(Scene scene, ScallingParams scallingParams, Parent contentPane) {
            this.scene = scene;
            this.scallingParams = scallingParams;
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
                contentPane.prefWidth(Math.max(scallingParams.initWidth, scene.getWidth()));
                contentPane.prefHeight(Math.max(scallingParams.initHeight, scene.getHeight()));
            }
        }

        private double calculateScaleFactor(double newWidth, double newHeight) {
            return newWidth / newHeight > scallingParams.ratio
                    ? newHeight / scallingParams.initHeight
                    : newWidth / scallingParams.initWidth;
        }
    }

    private class ScallingParams {
        private double initWidth;
        private double initHeight;
        private double ratio;

        private ScallingParams(double initWidth, double initHeight, double ratio) {
            this.initWidth = initWidth;
            this.initHeight = initHeight;
            this.ratio = ratio;
        }
    }
}

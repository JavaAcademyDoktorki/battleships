package com.battleships.windowScalling;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Enables scalling for a <code>{@link Scene}</code> and <code>{@link Parent}</code>
 */

public class WindowScalling {

    /**
     * Initialize <code>{@link SceneSizeChangeListener}</code>
     *
     * @param scene - a scene to be scalled
     * @param contentPane - a parent to be scalled
     */

    public void enableFor(final Scene scene, final Parent contentPane) {
        Scaling scaling = calculateScallingParams(scene);
        SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, scaling, contentPane);
        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);
    }

    private Scaling calculateScallingParams(Scene scene) {
        final double initWidth = scene.getWidth();
        final double initHeight = scene.getHeight();
        return new Scaling(initWidth, initHeight);
    }
}

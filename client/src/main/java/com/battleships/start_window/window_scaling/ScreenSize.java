package com.battleships.start_window.window_scaling;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenSize {
    private static final double GOLDEN_RATIO = 1.62;
    private final double width;
    private final double height;

    public ScreenSize(Screen screen) {
        Rectangle2D bounds = screen.getBounds();
        height = bounds.getHeight() / 2;
        width = GOLDEN_RATIO * height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

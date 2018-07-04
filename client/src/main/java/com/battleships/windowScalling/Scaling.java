package com.battleships.windowScalling;

class Scaling {
    private final double initialWidth;
    private final double initialHeight;
    private final double initialAppRatio;

    Scaling(double initialWidth, double initialHeight) {
        this.initialWidth = initialWidth;
        this.initialHeight = initialHeight;
        this.initialAppRatio = calculateRatio(initialWidth, initialHeight);
    }

    double getInitialWidth() {
        return initialWidth;
    }

    double getInitialHeight() {
        return initialHeight;
    }

    double calculateScaleFactor(double newWidth, double newHeight) {
        return isNewRatioGreaterThanInitial(newWidth, newHeight)
                ? calculateRatio(newHeight, initialHeight)
                : calculateRatio(newWidth, initialWidth);
    }

    private boolean isNewRatioGreaterThanInitial(double newWidth, double newHeight) {
        return calculateRatio(newWidth, newHeight) > initialAppRatio;
    }

    private double calculateRatio(double firstDimension, double secondDimension) {
        return firstDimension / secondDimension;
    }


}
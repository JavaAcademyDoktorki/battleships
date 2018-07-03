package com.battleships.windowScalling;

class ScalingParams {
    private double width;
    private double height;
    private double ratio;

    ScalingParams(double width, double height, double ratio) {
        this.width = width;
        this.height = height;
        this.ratio = ratio;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double calculateScaleFactor(double newWidth, double newHeight) {
        return newWidth / newHeight > ratio
                ? newHeight / height
                : newWidth / width;
    }
}
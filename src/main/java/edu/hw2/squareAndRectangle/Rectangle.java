package edu.hw2.squareAndRectangle;

public class Rectangle {
    private double width;
    private double height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double area() {
        return width * height;
    }
}

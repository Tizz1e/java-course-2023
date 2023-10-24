package edu.hw2.squareAndRectangle;

public class Rectangle {
    protected final double width;
    protected final double height;

    public Rectangle() {
        width = 0;
        height = 0;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, height);
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(width, height);
    }

    public double area() {
        return width * height;
    }
}

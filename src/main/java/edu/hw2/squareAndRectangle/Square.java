package edu.hw2.squareAndRectangle;

public class Square extends Rectangle {
    private int side;

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        side = width;
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        side = height;
    }

    public double getRealArea() {
        return side * side;
    }
}

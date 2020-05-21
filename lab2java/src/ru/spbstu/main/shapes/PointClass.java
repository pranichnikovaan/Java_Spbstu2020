package ru.spbstu.main.shapes;

public class PointClass implements Point {
    private float x, y;

    public PointClass() {
        x = 0;
        y = 0;
    }

    public PointClass(float targetX, float targetY) {
        x = targetX;
        y = targetY;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}

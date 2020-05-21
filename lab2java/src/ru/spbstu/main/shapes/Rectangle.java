package ru.spbstu.main.shapes;

/**
 * Представление о прямоугольнике.
 * <p>
 * Прямоугольник — четырехугольник, у которого все углы
 * прямые (равны 90 градусам).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D1%8F%D0%BC%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Прямоугольник</a>
 */
public class Rectangle implements Polygon {
    private PointClass center;
    private float width, height;
    private int angle = 0;

    public Rectangle(PointClass targetCenter, float targetWidth, float targetHeight, int targetAngle) {
        center = targetCenter;
        width = targetWidth;
        height = targetHeight;
        angle = targetAngle;
    }

    public Rectangle(PointClass targetCenter, float targetWidth, float targetHeight) {
        this(targetCenter, targetWidth, targetHeight, 0);
    }

    @Override
    public void rotate(int targetAngle) {
        angle += targetAngle;

        while (angle > 360) {
            angle -= 360;
        }
    }

    @Override
    public float getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public float getArea() {
        return width * height;
    }

    @Override
    public int getRotation() {
        return angle;
    }

    @Override
    public float getX() {
        return center.getX();
    }

    @Override
    public float getY() {
        return center.getY();
    }

    @Override
    public String getInfo() {
        return "Rectangle: " +
                "width = " + width + "; " +
                "height = " + height +  "; " +
                "center: (" + center.getX() + "; " + center.getY() + ")";
    }

    /*
     * TODO: Реализовать класс 'Rectangle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */
}

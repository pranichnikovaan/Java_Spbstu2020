package ru.spbstu.main.shapes;

import static java.lang.StrictMath.PI;

/**
 * Представление об окружности.
 * <p>
 * Окру́жность — замкнутая плоская кривая, которая состоит из
 * всех точек на плоскости, равноудалённых от заданной точки.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9E%D0%BA%D1%80%D1%83%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D1%8C">Окружность</a>
 */
public class Circle implements Ellipse{
    private PointClass center;
    private float radius;

    public Circle(PointClass targetPoint, float targetRadius) {
        center = targetPoint;
        radius = targetRadius;
    }

    @Override
    public float getLength() {
        return (float) (2 * PI * radius);
    }

    @Override
    public float getArea() {
        return (float) (PI * radius * radius);
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
        return "Circle: " +
                "radius = " + radius + "; " +
                "center: (" + center.getX() + "; " + center.getY() + ")";
    }

    /*
     * TODO: Реализовать класс 'Circle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */
}

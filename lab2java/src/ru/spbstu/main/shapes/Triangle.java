package ru.spbstu.main.shapes;

import static java.lang.StrictMath.sqrt;
/**
 * Представление о треугольнике.
 * <p>
 * Треуго́льник (в евклидовом пространстве) — геометрическая
 * фигура, образованная тремя отрезками, которые соединяют
 * три точки, не лежащие на одной прямой. Указанные три
 * точки называются вершинами треугольника, а отрезки —
 * сторонами треугольника. Часть плоскости, ограниченная
 * сторонами, называется внутренностью треугольника: нередко
 * треугольник рассматривается вместе со своей внутренностью
 * (например, для определения понятия площади).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%A2%D1%80%D0%B5%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Треугольник</a>
 */
public class Triangle implements Polygon {
    private PointClass center;
    private float firstSide, secondSide, thirdSide;
    private int angle = 0;

    public Triangle(PointClass targetCenter, float targetFirstSide, float targetSecondSide, float targetThirdSide, int targetAngle) {
        if ((targetFirstSide > targetSecondSide + targetThirdSide) || (targetSecondSide > targetFirstSide + targetThirdSide) || (targetThirdSide > targetFirstSide + targetSecondSide)) {
            throw new IllegalArgumentException("Illegal input parameters");
        }

        center = targetCenter;
        firstSide = targetFirstSide;
        secondSide = targetSecondSide;
        thirdSide = targetThirdSide;
        angle = targetAngle;
    }

    public Triangle(PointClass targetCenter, float targetFirstSide, float targetSecondSide, float targetThirdSide) {
        this(targetCenter, targetFirstSide, targetSecondSide, targetThirdSide, 0);
    }

    @Override
    public void rotate(int targetAngle) {
        angle = targetAngle;
    }

    @Override
    public float getPerimeter() {
        return firstSide + secondSide + thirdSide;
    }

    @Override
    public float getArea() {
        float p = getPerimeter() / 2;
        return (float) (sqrt(p * (p - firstSide) * (p - secondSide) * (p - thirdSide)));
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
        return "Triangle: " +
                "first side = " + firstSide + "; " +
                "second side = " + secondSide + "; " +
                "third side = " + thirdSide + "; " +
                "center: (" + center.getX() + "; " + center.getY() + ")";
    }

    /*
     * TODO: Реализовать класс 'Triangle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */
}




import java.util.Random;

public class Main {
    public static Shape getMaxAreaShape(Shape[] shapes) {
        float maxArea = 0;
        int maxAreaShapeIndex = 0;
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i].getArea() > maxArea) {
                maxArea = shapes[i].getArea();
                maxAreaShapeIndex = i;
            }
        }

        return shapes[maxAreaShapeIndex];
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[10];;
        Random random = new Random();
        try {
            for (int i = 0; i < shapes.length; i += 3) {
                if (i == shapes.length - 1) {
                    shapes[i] = new Circle(new PointClass(random.nextFloat() * 10, random.nextFloat() * 10), random.nextFloat() * 10);
                    break;
                }
                shapes[i] = new Circle(new PointClass(random.nextFloat() * 10, random.nextFloat() * 10), random.nextFloat() * 10);
                shapes[i + 1] = new Triangle(new PointClass(random.nextFloat() * 10, random.nextFloat() * 10), random.nextFloat() * (10 - 5) + 5, random.nextFloat() * (10 - 6) + 6, random.nextFloat() * (10 - 7) + 7);
                shapes[i + 2] = new Rectangle(new PointClass(random.nextFloat() * 10, random.nextFloat() * 10), random.nextFloat() * 10, random.nextFloat() * 10);
            }

            for (Shape shape : shapes) {
                System.out.println(shape.getArea() + " - the area of a " + shape.getInfo());
            }

            System.out.println();
            System.out.println("Shape with max area - " + getMaxAreaShape(shapes).getArea() + " - " + getMaxAreaShape(shapes).getInfo());
        } catch (IllegalArgumentException ex) {
            System.err.println("Illegal arguments");
        }

    }
}
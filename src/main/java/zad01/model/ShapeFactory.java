package zad01.model;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
    private final Map<Double, Circle> circleCache = new HashMap<>();
    private final Map<Double, Square> squareCache = new HashMap<>();
    private final Map<String, Rectangle> rectangleCache = new HashMap<>();

    public Circle createCircle(double radius) {
        if (!circleCache.containsKey(radius)) {
            circleCache.put(radius, new Circle(radius));
        }
        return circleCache.get(radius);
    }

    public Square createSquare(double sideLength) {
        if (!squareCache.containsKey(sideLength)) {
            squareCache.put(sideLength, new Square(sideLength));
        }
        return squareCache.get(sideLength);
    }

    public Rectangle createRectangle(double length, double width) {
        String key = length + "_" + width;
        if (!rectangleCache.containsKey(key)) {
            rectangleCache.put(key, new Rectangle(length, width));
        }
        return rectangleCache.get(key);
    }
}

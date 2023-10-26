package zad01.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ShapeFactoryTest {
    @Test
    public void testCreateSquare() {
        ShapeFactory factory = new ShapeFactory();
        Square square1 = factory.createSquare(5.0);
        Square square2 = factory.createSquare(5.0);
        assertSame(square1, square2);
        assertEquals(25.0, square1.area(),0.0);
        assertEquals("square", square1.getType());
    }

    @Test
    public void testCreateCircle() {
        ShapeFactory factory = new ShapeFactory();
        Circle circle1 = factory.createCircle(5.0);
        Circle circle2 = factory.createCircle(5.0);
        assertSame(circle1, circle2);
        assertEquals(Math.PI * 25.0, circle1.area(), 0.0001);
        assertEquals("circle", circle1.getType());
    }

    @Test
    public void testCreateRectangle() {
        ShapeFactory factory = new ShapeFactory();
        Rectangle rectangle1 = factory.createRectangle(5.0, 3.0);
        Rectangle rectangle2 = factory.createRectangle(5.0, 3.0);
        assertSame(rectangle1, rectangle2);
        assertEquals(15.0, rectangle1.area(),0.0);
        assertEquals("rectangle", rectangle1.getType());
    }
}

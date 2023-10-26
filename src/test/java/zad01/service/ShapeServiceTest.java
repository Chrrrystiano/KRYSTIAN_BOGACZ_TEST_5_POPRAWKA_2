package zad01.service;

import org.junit.Before;
import org.junit.Test;
import zad01.exceptions.TheGivenFigureListIsEmptyException;
import zad01.model.Circle;
import zad01.model.Rectangle;
import zad01.model.Shape;
import zad01.model.Square;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ShapeServiceTest {
    private ShapeService shapeService;

    @Before
    public void setUp() {
        shapeService = new ShapeService();
    }

    @Test(expected = TheGivenFigureListIsEmptyException.class)
    public void exportShapesToJSON_EmptyList() throws IOException {
        List<Shape> shapes = Collections.emptyList();
        String path = "testpath.json";
        shapeService.exportShapesToJSON(shapes, path);
    }

    @Test
    public void exportShapesToJSON_ValidListAndPath() throws IOException {
        Shape shape1 = mock(Shape.class);
        Shape shape2 = mock(Shape.class);
        List<Shape> shapes = Arrays.asList(shape1, shape2);
        String path = "testpath.json";
        shapeService.exportShapesToJSON(shapes, path);
        File file = new File(path);
        assertTrue(file.exists());
    }

    @Test
    public void exportShapesToJSON_InvalidPath() throws IOException {
        Shape shape = mock(Shape.class);
        List<Shape> shapes = Collections.singletonList(shape);
        String path = "testpath.json";
        shapeService.exportShapesToJSON(shapes, path);
    }

    @Test
    public void importShapesFromJSON_ValidPath() throws IOException {
        File tempFile = File.createTempFile("testfile", ".json");
        String jsonContent = "[{\"type\":\"circle\",\"radius\":5.0}]";
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(jsonContent);
        }
        List<Shape> shapes = shapeService.importShapesFromJSON(tempFile.getAbsolutePath());
        assertNotNull(shapes);
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof Circle);
        assertEquals(5.0, ((Circle) shapes.get(0)).getRadius(), 0.001);
        tempFile.delete();
    }

    @Test
    public void testFindShapeWithLargestArea_ValidList() {
        List<Shape> shapes = Arrays.asList(
                new Square(5),
                new Rectangle(4, 10)
        );
        Shape largestShape = shapeService.findShapeWithLargestArea(shapes);
        assertEquals(largestShape, new Rectangle(4, 10));
    }

    @Test(expected = TheGivenFigureListIsEmptyException.class)
    public void testFindShapeWithLargestArea_EmptyList() {
        List<Shape> shapes = Collections.emptyList();
        shapeService.findShapeWithLargestArea(shapes);
    }

    @Test
    public void testFindShapeWithLargestArea_SingleElementList() {
        Circle singleCircle = new Circle(3);
        List<Shape> shapes = Collections.singletonList(singleCircle);
        Shape largestShape = shapeService.findShapeWithLargestArea(shapes);
        assertEquals(largestShape, singleCircle);
    }

    @Test
    public void testFindShapeWithLargestPerimeterOfType_ValidListAndType() {
        List<Shape> shapes = Arrays.asList(
                new Circle(5),
                new Rectangle(4, 6),
                new Rectangle(40, 60),
                new Rectangle(5, 60),
                new Square(3)
        );
        Shape largestPerimeterShape = shapeService.findShapeWithLargestPerimeterOfType(shapes, "rectangle");
        assertEquals(largestPerimeterShape, new Rectangle(40, 60));
    }

    @Test(expected = TheGivenFigureListIsEmptyException.class)
    public void testFindShapeWithLargestPerimeterOfType_EmptyList() {
        List<Shape> shapes = Arrays.asList();
        shapeService.findShapeWithLargestPerimeterOfType(shapes, "rectangle");
    }

    @Test
    public void testFindShapeWithLargestPerimeterOfType_NonExistentType() {
        List<Shape> shapes = Arrays.asList(
                new Circle(5),
                new Rectangle(10, 20),
                new Square(8)
        );
        Shape largestPerimeterShape = shapeService.findShapeWithLargestPerimeterOfType(shapes, "triangle");
        assertNull(largestPerimeterShape);
    }

    @Test
    public void testExportShapesToJSON_EmptyList() {
        ShapeService shapeService = new ShapeService();
        List<Shape> emptyList = Collections.emptyList();
        String path = "testpath.json";

        try {
            shapeService.exportShapesToJSON(emptyList, path);
            fail("Expected TheGivenFigureListIsEmptyException, but no exception was thrown.");
        } catch (TheGivenFigureListIsEmptyException e) {
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getClass().getSimpleName());
        }
    }
}

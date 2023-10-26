package zad01.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import zad01.exceptions.CannotSaveToFileException;
import zad01.exceptions.FileNottFoundException;
import zad01.exceptions.TheGivenFigureListIsEmptyException;
import zad01.model.Shape;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ShapeService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void exportShapesToJSON(List<Shape> shapes, String path) throws IOException {
        if (shapes == null || shapes.isEmpty()) {
            throw new TheGivenFigureListIsEmptyException("Shapes list is empty.");
        }
        File file = new File(path);
        if (file.exists() && !file.canWrite()) {
            throw new CannotSaveToFileException("Cannot write to the specified path: " + path);
        }
        objectMapper.writeValue(file, shapes);
    }

    public List<Shape> importShapesFromJSON(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNottFoundException("File not found at path: " + path);
        }
        return objectMapper.readValue(file, new TypeReference<List<Shape>>() {
        });
    }

    public Shape findShapeWithLargestArea(List<Shape> shapes) {
        if (shapes == null || shapes.isEmpty()) {
            throw new TheGivenFigureListIsEmptyException("Shape list is empty");
        }
        return shapes.stream()
                .max(Comparator.comparing(Shape::area))
                .orElse(null);
    }

    public Shape findShapeWithLargestPerimeterOfType(List<Shape> shapes, String type) {
        if (shapes == null || shapes.isEmpty()) {
            throw new TheGivenFigureListIsEmptyException("Shape list is empty");
        }
        return shapes.stream()
                .filter(shape -> shape.getType().equals(type))
                .max(Comparator.comparing(Shape::perimeter))
                .orElse(null);
    }
}

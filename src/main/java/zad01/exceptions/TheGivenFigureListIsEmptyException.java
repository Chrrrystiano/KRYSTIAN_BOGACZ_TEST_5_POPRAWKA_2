package zad01.exceptions;

public class TheGivenFigureListIsEmptyException extends RuntimeException {
    public TheGivenFigureListIsEmptyException() {
    }

    public TheGivenFigureListIsEmptyException(String message) {
        super(message);
    }
}

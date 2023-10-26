package zad01.exceptions;

public class CannotSaveToFileException extends RuntimeException {
    public CannotSaveToFileException() {
    }

    public CannotSaveToFileException(String message) {
        super(message);
    }
}

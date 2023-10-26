package zad02.exceptions;

public class InvalidJsonFormatException extends RuntimeException {
    public InvalidJsonFormatException() {
    }

    public InvalidJsonFormatException(String message) {
        super(message);
    }

    public InvalidJsonFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}

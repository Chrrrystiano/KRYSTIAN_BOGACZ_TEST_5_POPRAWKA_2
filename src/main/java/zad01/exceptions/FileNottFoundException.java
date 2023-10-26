package zad01.exceptions;

public class FileNottFoundException extends RuntimeException {
    public FileNottFoundException() {
    }

    public FileNottFoundException(String message) {
        super(message);
    }
}

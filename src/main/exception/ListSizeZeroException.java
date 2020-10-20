package exception;

public class ListSizeZeroException extends Exception {
    public ListSizeZeroException() {
        super();
    }

    public ListSizeZeroException(String message) {
        super(message);
    }
}

package exception;

public class NotInTheListException extends Exception {
    public NotInTheListException() {
    }

    public NotInTheListException(String message) {
        super(message);
    }
}

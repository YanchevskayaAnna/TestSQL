package exceptions;

public class NotFoundIDColumnException extends Exception{
    public NotFoundIDColumnException() {
    }

    public NotFoundIDColumnException(String message) {
        super(message);
    }
}

package by.tr.library.dao.exception;

/**
 * Created by Ivan on 16.07.2016.
 */
public class ConnectionPoolException extends Exception{

    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}

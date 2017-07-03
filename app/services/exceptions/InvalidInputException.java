package services.exceptions;

/**
 * Created by knvarma on 30/06/17.
 */
public class InvalidInputException extends Throwable {

    public InvalidInputException() {
    }

    public InvalidInputException(String reason) {
        super(reason);
    }
}

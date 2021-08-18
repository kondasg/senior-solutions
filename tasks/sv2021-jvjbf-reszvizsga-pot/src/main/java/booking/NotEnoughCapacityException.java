package booking;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NotEnoughCapacityException extends AbstractThrowableProblem {

    public NotEnoughCapacityException(String message) {
        super(URI.create("accommodation/bad-reservation"),
                "Bad reservation",
                Status.BAD_REQUEST,
                message);
    }
}

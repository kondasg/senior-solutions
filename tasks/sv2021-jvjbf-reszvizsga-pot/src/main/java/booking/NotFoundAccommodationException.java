package booking;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NotFoundAccommodationException extends AbstractThrowableProblem {

    public NotFoundAccommodationException(String message) {
        super(URI.create("accommodation/not-found"),
                "Not found",
                Status.NOT_FOUND,
                message);
    }

}

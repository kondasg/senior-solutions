package lineorders;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NotOrderExpcetion extends AbstractThrowableProblem {

    public NotOrderExpcetion(String message) {
        super(URI.create("order/not-found"),
                "Not found",
                Status.NOT_FOUND,
                message);
    }
}

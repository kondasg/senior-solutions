package appointment;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateValidator implements ConstraintValidator<Date, Interval> {

    @Override
    public boolean isValid(Interval interval, ConstraintValidatorContext constraintValidatorContext) {
        return interval.getStart().isAfter(LocalDateTime.now())
                && interval.getEnd().isAfter(interval.getStart());
    }
}

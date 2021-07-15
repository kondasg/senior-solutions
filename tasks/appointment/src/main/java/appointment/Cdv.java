package appointment;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CdvValidator.class)
public @interface Cdv {

    String message() default "Invalid taxnumber";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

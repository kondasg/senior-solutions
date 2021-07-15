package appointment;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsCaseTypeValidator.class)
public @interface IsCaseType {

    String message() default "Invalid Case Type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

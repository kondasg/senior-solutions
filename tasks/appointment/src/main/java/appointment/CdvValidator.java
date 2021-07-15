package appointment;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CdvValidator implements ConstraintValidator<Cdv, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.length() != 10) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            try {
                sum += (i + 1) * Integer.parseInt(s.substring(i, i + 1));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return sum % 11 == Integer.parseInt(s.substring(9, 10));
    }
}

package appointment;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsCaseTypeValidator implements ConstraintValidator<IsCaseType, String> {

    private NavService navService;

    @Autowired
    public IsCaseTypeValidator(NavService navService) {
        this.navService = navService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return navService.getCaseTypes().stream()
                .anyMatch(caseType -> caseType.getId().contains(value));
    }
}

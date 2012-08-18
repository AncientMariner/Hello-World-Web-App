package org.xander.HWTO.validation.validators;

import org.xander.HWTO.validation.annotations.PasswordStrength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator fo {@link PasswordStrength}. Checks the password strength.
 *
 * @see PasswordStrength
 */
public class PasswordStrengthValidator implements ConstraintValidator<PasswordStrength, String> {
    private Pattern pattern = Pattern.compile(
            "((?=.*\\d)(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"
    );

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(PasswordStrength constraintAnnotation) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        Matcher matcher = pattern.matcher(object);

        return matcher.matches();
    }
}

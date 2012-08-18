package org.xander.HWTO.validation.validators;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.xander.HWTO.validation.annotations.Matches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link Matches}. Checks the equality of the two properties.
 *
 * @see Matches
 */
public class MatchesValidator implements ConstraintValidator<Matches, Object> {

    private String firstPropertyName;
    private String secondPropertyName;
    private String failMessage;
    private String fieldValue1;
    private String fieldValue2;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Matches constraintAnnotation) {
        this.firstPropertyName = constraintAnnotation.field();
        this.secondPropertyName = constraintAnnotation.verifyField();
        this.failMessage = constraintAnnotation.message();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        getComparableFields(value);

        boolean matches = StringUtils.equals(fieldValue1, fieldValue2);
        if (!matches) {
            constraintViolated(context);
        }
        return matches;
    }

    /**
     * Put constraint violation into context.
     *
     * @param context validator context
     */
    private void constraintViolated(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(failMessage)
                .addNode(secondPropertyName)
                .addConstraintViolation();
    }

    /**
     * Retrieving comparable fields from object.
     * Throws {@code IllegalStateException} whether the field is not found.
     *
     * @param value object from which value of the fields are taken
     */
    private void getComparableFields(Object value) {
        try {
            fieldValue2 = BeanUtils.getProperty(value, firstPropertyName);
            fieldValue1 = BeanUtils.getProperty(value, secondPropertyName);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
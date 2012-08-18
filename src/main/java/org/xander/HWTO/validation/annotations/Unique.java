package org.xander.HWTO.validation.annotations;

import org.xander.HWTO.validation.validators.UserUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation for field checking whether its value does not exist in a database.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserUniqueValidator.class)
@Documented
public @interface Unique {
    /**
     * Message for display when validation fails.
     */
    String message() default "{validation.username_uniqueness}";

    /**
     * Groups element that specifies the processing groups with which the
     * constraint declaration is associated.
     */
    Class<?>[] groups() default {};

    /**
     * Payload element that specifies the payload with which the the
     * constraint declaration is associated.
     */
    Class<? extends Payload>[] payload() default {};


}

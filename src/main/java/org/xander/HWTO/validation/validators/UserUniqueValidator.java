package org.xander.HWTO.validation.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.xander.HWTO.model.entity.Persistent;
import org.xander.HWTO.model.entity.User;
import org.xander.HWTO.service.UserService;
import org.xander.HWTO.validation.annotations.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator fo {@link Unique}. Checks the user uniqueness.
 *
 * @see Unique
 */
@ContextConfiguration(locations = {"classpath:/org/xander/HWTO/service/applicationContext-service.xml", "classpath:/org/xander/HWTO/model/applicationContext-dao.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserUniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    public UserUniqueValidator(UserService userService) {
        this.userService = userService;
    }

    UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Unique annotation) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService.uniquenessNameCheck(value);

    }
}

package org.xander.HWTO.validation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.xander.HWTO.model.dao.UserDao;
import org.xander.HWTO.service.UserService;
import org.xander.HWTO.utils.SecurityUtils;
import org.xander.HWTO.validation.validators.UserUniqueValidator;

import javax.validation.ConstraintValidatorContext;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Test for user name validation, whether user is already present in the database.
 */
@ContextConfiguration(locations = {"classpath:/org/xander/HWTO/service/applicationContext-service.xml", "classpath:/org/xander/HWTO/model/applicationContext-dao.xml"})
public class UserNameUniquenessTest {

    @Mock
    private UserDao userDao;

    @Mock
    private SecurityUtils securityUtils;
    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void userNameUniquenessTest() {

        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        when(userService.uniquenessNameCheck(anyString())).thenReturn(true);

        UserUniqueValidator userUniqueValidator = new UserUniqueValidator(userService);
        boolean result = userUniqueValidator.isValid(anyString(), constraintValidatorContext);

        assertTrue(result);

    }

    @Test
    public void userNameNonUniquenessTest() {

        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        when(userService.uniquenessNameCheck(anyString())).thenReturn(false);

        UserUniqueValidator userUniqueValidator = new UserUniqueValidator(userService);
        boolean result = userUniqueValidator.isValid(anyString(), constraintValidatorContext);

        assertFalse(result);

    }
}

package org.xander.HWTO.validation;

import org.junit.Test;
import org.xander.HWTO.validation.annotations.Matches;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Test for password matching validation.
 */
public class PasswordMatchesTest {
    @Test
    public void matchingPasswordsPositiveTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        String password = "Password";
        String passwordConfirm = "Password";

        int expectedErrorQty = 0;

        Set<ConstraintViolation<TestObject>> constraintViolation = validator.validate(new TestObject(password, passwordConfirm));
        assertEquals(expectedErrorQty, constraintViolation.size());
    }

    @Test
    public void matchingPasswordsNegativeTest() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        String password = "AnotherPassword";
        String passwordConfirm = "Password";

        int expectedErrorQty = 1;

        Set<ConstraintViolation<TestObject>> constraintViolation = validator.validate(new TestObject(password, passwordConfirm));
        assertEquals(expectedErrorQty, constraintViolation.size());

        constraintViolation = validator.validate(new TestObject(password + "Suffix", "Another" + passwordConfirm));
        assertEquals(expectedErrorQty, constraintViolation.size());
    }


    @Matches(field = "password", verifyField = "passwordConfirm")
    public class TestObject {
        private String password;
        private String passwordConfirm;

        public TestObject(String password, String passwordConfirm) {
            this.password = password;
            this.passwordConfirm = passwordConfirm;
        }

        public String getPassword() {
            return password;
        }

        public String getPasswordConfirm() {
            return passwordConfirm;
        }
    }
}

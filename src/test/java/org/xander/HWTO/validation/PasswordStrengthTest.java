package org.xander.HWTO.validation;

import org.junit.Test;
import org.xander.HWTO.validation.annotations.PasswordStrength;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Test for password strength validation.
 */
public class PasswordStrengthTest {
    @Test
    public void passwordStrengthInvalidityTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //Password should contain at least 1 digit, 1 uppercase, 1 lowercase, 1 specific symbol

        TestObject testObject = new TestObject();
        String[] invalidPasswords = {
                "lowercase",
                "12345",
                "UPPERCASE",
                "!@#$%",
                "lowerUPPER",
                "lowerUPPERDIGIT4",
                "lowerdigit1",
                "UPPERDIGIT12",
                "lowerspecific%",
                "lowerUPPERSPECIFIC(",
                "A@a"
        };

        int expectedErrorQty = 1;

        for (String password : invalidPasswords) {
            testObject.setPassword(password);
            Set<ConstraintViolation<TestObject>> constraintViolation = validator.validate(testObject);

            assertEquals(expectedErrorQty, constraintViolation.size());
        }
    }

    @Test
    public void passwordStrengthValidityTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //Password should contain at least 1 digit, 1 uppercase, 1 lowercase, 1 specific symbol

        TestObject testObject = new TestObject();

        String[] validPasswords = {
                "1!qQq",
                "aA@#76",
                "UPPERCASElower1!",
                "!@#$%aA25",
                "1lower^UPPER*",
                "^lowerUPPERDIGIT4^",
                "^Lowerdigit1",
                "uPPERDIGIT12#",
                "lowerspecific%5A",
                "2lowerUPPERSPECIFIC(",
                "A@a777"
        };

        int expectedErrorQty = 0;

        for (String password : validPasswords) {
            testObject.setPassword(password);
            Set<ConstraintViolation<TestObject>> constraintViolation = validator.validate(testObject);

            assertEquals(expectedErrorQty, constraintViolation.size());
        }
    }

    public class TestObject {

        @PasswordStrength
        private String password;

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

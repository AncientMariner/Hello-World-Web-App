package org.xander.HWTO.forms;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Test for user register form, that verifies work with register object representation data.
 */
public class UserRegisterFormTest {

    @Test
    public void userRegisterFormTest() {
        String userName = "userName";
        String password = "password";
        String passwordConfirm = "passwordConfirm";

        UserRegisterForm userRegisterForm = new UserRegisterForm();
        userRegisterForm.setUsername(userName);
        userRegisterForm.setPassword(password);
        userRegisterForm.setPasswordConfirm(passwordConfirm);

        assertEquals(userName, userRegisterForm.getUsername());
        assertEquals(password, userRegisterForm.getPassword());
        assertEquals(passwordConfirm, userRegisterForm.getPasswordConfirm());
    }
}

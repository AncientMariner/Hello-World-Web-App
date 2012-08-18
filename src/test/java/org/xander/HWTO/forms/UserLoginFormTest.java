package org.xander.HWTO.forms;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Test for user login form, that verifies work with login object representation data.
 */
public class UserLoginFormTest {

    @Test
    public void userLoginFormTest() {
        String userName = "userName";
        String password = "password";

        UserLoginForm userLoginForm = new UserLoginForm();
        userLoginForm.setUsername(userName);
        userLoginForm.setPassword(password);

        assertEquals(userName, userLoginForm.getUsername());
        assertEquals(password, userLoginForm.getPassword());
    }
}

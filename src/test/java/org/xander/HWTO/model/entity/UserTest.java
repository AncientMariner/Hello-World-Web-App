package org.xander.HWTO.model.entity;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Test for user entity
 */
public class UserTest {
    private User user;

    @Test
    public void userTest() {
        user = new User("UserName", "Password", "Salt");

        String username = "UserName";
        String password = "Password";
        String salt = "Salt";

        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(salt, user.getSalt());
    }

}

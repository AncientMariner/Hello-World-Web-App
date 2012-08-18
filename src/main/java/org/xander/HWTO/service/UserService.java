package org.xander.HWTO.service;

import org.xander.HWTO.forms.UserRegisterForm;
import org.xander.HWTO.model.entity.User;

/**
 * Interface for User service, that allows adding, getting user,checking for password equality and uniqueness checking.
 */
public interface UserService {
    /**
     * Adds user using register form of register object representation.
     *
     * @param registerUserForm form of the register object representation in view
     */
    void addUser(UserRegisterForm registerUserForm);

    /**
     * Gets user by specific user name.
     *
     * @param username specific username
     * @return returns User with appropriate username
     */
    User getByUserName(String username);

    /**
     * Checking of user password equality.
     *
     * @param user        - user with appropriate password to check
     * @param rawPassword - raw password of user, that needs to be checked (it is raw, without salt)
     * @return returns {@code true} whether user password and password confirmation are equal, otherwise {@code false}
     */
    boolean checkUserPasswordEquality(User user, String rawPassword);

    /**
     * Checking for user existence in database
     *
     * @param username username of the user - main criteria for checking in DB
     * @return returns {@code true} whether user already exists in database otherwise {@code false}
     */
    boolean uniquenessNameCheck(String username);

}

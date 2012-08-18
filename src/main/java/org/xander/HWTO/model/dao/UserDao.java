package org.xander.HWTO.model.dao;

import org.xander.HWTO.model.entity.User;

/**
 * Interface that is specific for user and allows getting user by username and checking user uniqueness in DB.
 */
public interface UserDao extends Dao<User> {

    /**
     * Gets User by username.
     *
     * @param name name of the user
     * @return returns User object that corresponds appropriate entered username
     */
    User getByUserName(String name);

    /**
     * Verifies whether username is unique (user with specific name does not exist in DB).
     *
     * @param name name of the user to check
     * @return returns {@code true} whether user with specific name already exists in DB otherwise returns {@code false}
     */
    boolean isUserWithGivenUserNameExists(String name);
}

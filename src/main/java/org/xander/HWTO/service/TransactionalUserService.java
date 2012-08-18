package org.xander.HWTO.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xander.HWTO.forms.UserRegisterForm;
import org.xander.HWTO.model.dao.UserDao;
import org.xander.HWTO.model.entity.User;
import org.xander.HWTO.utils.SecurityUtils;

/**
 * Transactional user service that implements {@link UserService} and overrides methods from it.
 */
@Service(value = "userService")
@Transactional
public class TransactionalUserService implements UserService {


    UserDao userDao;
    SecurityUtils securityUtils;

    public TransactionalUserService(UserDao userDao, SecurityUtils securityUtils) {
        this.userDao = userDao;
        this.securityUtils = securityUtils;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUser(UserRegisterForm registerUserForm) {

        String salt = securityUtils.generateSalt();
        String passwordHash = securityUtils.generateHash(salt, registerUserForm.getPassword());

        User user = new User(registerUserForm.getUsername(), passwordHash, salt);
        userDao.saveOrUpdate(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getByUserName(String username) {
        return userDao.getByUserName(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUserPasswordEquality(User user, String rawPassword) {
        String expectedHash = securityUtils.generateHash(user.getSalt(), rawPassword);
        return expectedHash.equals(user.getPassword());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean uniquenessNameCheck(String username) {
        return !userDao.isUserWithGivenUserNameExists(username);
    }
}

package org.xander.HWTO.model.dao.hibernate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.xander.HWTO.model.dao.UserDao;
import org.xander.HWTO.model.entity.User;

import static org.junit.Assert.assertEquals;

/**
 * Test for user hibernate dao.
 */
@ContextConfiguration(locations = {"classpath:/org/xander/HWTO/model/applicationContext-dao.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserHibernateDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void getTest() {

        User user = new User("UserName", "Password", "Salt");
        userDao.saveOrUpdate(user);

        User result = userDao.get(user.getId());

        assertEquals(result.getId(), user.getId());
    }

    @Test
    public void getByUserNameTest() {

        String userName = "UserName";
        User user = new User(userName, "Password", "Salt");

        userDao.saveOrUpdate(user);

        User result = userDao.getByUserName(userName);

        assertEquals(user, result);
    }

    @Test
    public void isUserWithGivenUserNameExistsTest() {
        String userName = "userName";

        User user = new User(userName, "password", "salt");
        userDao.saveOrUpdate(user);

        boolean expectedResult = true;
        boolean givenResult = userDao.isUserWithGivenUserNameExists(userName);

        assertEquals(expectedResult, givenResult);
    }

    @Test
    public void isUserWithGivenUserNameNotExistsTest() {
        String userName = "userName";

        boolean expectedResult = false;
        boolean givenResult = userDao.isUserWithGivenUserNameExists(userName);

        assertEquals(expectedResult, givenResult);
    }
}

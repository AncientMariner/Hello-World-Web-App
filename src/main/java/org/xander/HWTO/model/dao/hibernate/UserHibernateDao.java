package org.xander.HWTO.model.dao.hibernate;

import org.xander.HWTO.model.dao.UserDao;
import org.xander.HWTO.model.entity.User;

/**
 * User dao for working with hibernate.
 */
public class UserHibernateDao extends AbstractHibernateDao<User> implements UserDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public User getByUserName(String username) {
        return (User) getSession().
                createQuery("from User as P where P.username= :username").
                setString("username", username).
                uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserWithGivenUserNameExists(String username) {

        return ((Long) getSession().
                createQuery("select count(username) from User as P where P.username= :username").
                setString("username", username).
                uniqueResult() > 0);
    }
}

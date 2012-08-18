package org.xander.HWTO.model.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.xander.HWTO.model.dao.Dao;
import org.xander.HWTO.model.entity.Persistent;

import java.lang.reflect.ParameterizedType;

/**
 * Abstract hibernate dao that allows basic operations with user for hibernate implementation.
 *
 * @param <T> - entity that extends {@link Persistent} interface (for current situation - {@link org.xander.HWTO.model.entity.User} entity)
 */
public abstract class AbstractHibernateDao<T extends Persistent> implements Dao<T> {
    private SessionFactory sessionFactory;

    /**
     * Type of entity.
     */
    private final Class<T> type = getType();

    /**
     * Retrieves parametrized type of entity using reflection.
     *
     * @return returns type of entity
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Sets session factory to session factory.
     *
     * @param sessionFactory current session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets session from session factory.
     *
     * @return returns session factory
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdate(T persistent) {
        getSession().save(persistent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        getSession().
                createQuery("delete " + getType().getCanonicalName() + " as P where P.id= :id").
                setLong("id", id).
                executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T persistent) {
        getSession().delete(persistent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(Long id) {
        return (T) getSession().get(type, id);
    }
}

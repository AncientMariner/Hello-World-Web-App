package org.xander.HWTO.model.dao;

import org.xander.HWTO.model.entity.Persistent;

/**
 * Dao interface that represents abstract methods for basic operations with user entity.
 *
 * @param <T> - entity that extends {@link Persistent} interface (for current situation - {@link org.xander.HWTO.model.entity.User} entity)
 */
public interface Dao<T extends Persistent> {
    /**
     * Deletes an entity with specific id.
     *
     * @param id id of the entity in db
     */
    void delete(Long id);

    /**
     * Deletes an entity, that extends persistent.
     *
     * @param persistent - entity that is persistent
     */
    void delete(T persistent);

    /**
     * Gets a persistent entity by id.
     *
     * @param id id of the persistent entity
     * @return returns persistent entity corresponding to entered id
     */
    T get(Long id);

    /**
     * Saves or updates persistent entity.
     *
     * @param persistent persistent entity, that need to be saved or updated
     */
    void saveOrUpdate(T persistent);
}

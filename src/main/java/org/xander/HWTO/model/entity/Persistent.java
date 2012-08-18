package org.xander.HWTO.model.entity;

/**
 * Persistent interface that adds methods for working with ID to the user, that allows interaction with DB.
 */
public interface Persistent {

    /**
     * Gets ID of the persistent entity.
     *
     * @return id of the persistent entity
     */
    Long getId();

    /**
     * Sets ID of the persistent entity.
     *
     * @param id id to set to the persistent entity in the DB
     */
    void setId(Long id);
}

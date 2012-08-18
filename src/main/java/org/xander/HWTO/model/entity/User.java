package org.xander.HWTO.model.entity;

/**
 * User class responsible for user entity, that implements {@link Persistent} interface.
 */
public class User implements Persistent {

    private Long id;
    private String username;

    private String password;
    private String salt;

    public User(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    protected User() {
    }

    /**
     * Gets username of the user.
     *
     * @return string of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets  username to specified user.
     *
     * @param username username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password of the user.
     *
     * @return returns password of the specified user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password of user.
     *
     * @param password password of the specified user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets salt of the user.
     *
     * @return returns salt of the specified user
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets salt of the user.
     *
     * @param salt salt to add to user password
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }
}

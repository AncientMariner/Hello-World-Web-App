package org.xander.HWTO.forms;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Login object representation form.
 */
public class UserLoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    /**
     * Gets username from login object representation.
     *
     * @return returns username from login object representation
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username on login object representation.
     *
     * @param username username to write on login object representation
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password from login object representation.
     *
     * @return returns password from login object representation
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password on login object representation.
     *
     * @param password password to write on login object representation
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

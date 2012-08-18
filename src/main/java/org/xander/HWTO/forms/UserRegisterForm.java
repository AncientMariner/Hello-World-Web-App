package org.xander.HWTO.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.xander.HWTO.validation.annotations.Matches;
import org.xander.HWTO.validation.annotations.PasswordStrength;
import org.xander.HWTO.validation.annotations.Unique;

import javax.validation.constraints.Size;

/**
 * Register object representation form.
 */
@Matches(field = "password", verifyField = "passwordConfirm", message = "{validation.password_confirmation_equality}")
public class UserRegisterForm {


    private static final String USERNAME_ILLEGAL_LENGTH = "{user.username.invalid_length}";
    private static final String PASSWORD_ILLEGAL_LENGTH = "{user.password.invalid_length}";

    private static final int USERNAME_MIN_LENGTH = 3;
    private static final int USERNAME_MAX_LENGTH = 25;
    private static final int PASSWORD_MIN_LENGTH = 5;
    private static final int PASSWORD_MAX_LENGTH = 25;

    @NotBlank
    @Size(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH, message = USERNAME_ILLEGAL_LENGTH)
    @Unique(message = "{validation.username_uniqueness}")
    private String username;

    @NotBlank
    @PasswordStrength
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = PASSWORD_ILLEGAL_LENGTH)
    private String password;

    private String passwordConfirm;

    /**
     * Gets username from registration object representation.
     *
     * @return returns entered username from registration object representation
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username on registration object representation.
     *
     * @param username username to write to registration object representation
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password from registration object representation.
     *
     * @return returns entered password from registration object representation
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password on registration object representation.
     *
     * @param password password to write to registration object representation
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets password confirmation from registration object representation.
     *
     * @return returns entered password confirmation from registration object representation
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * Sets password confirmation on registration object representation.
     *
     * @param passwordConfirm password confirmation to write to registration object representation
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}

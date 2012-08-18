package org.xander.HWTO.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Security utils for salt generation and for password hash generation.
 */
public class SecurityUtils {

    /**
     * Generation of the salt.
     *
     * @return returns generated salt in string.
     */
    public String generateSalt() {

        final int SALT_SIZE = 32;
        return RandomStringUtils.randomAlphanumeric(SALT_SIZE);

    }

    /**
     * Generation of the hash for entered raw user password using {@link org.apache.commons.codec.digest.DigestUtils#sha256Hex} algorithm.
     *
     * @param salt     salt that should be added to password
     * @param password raw password, just entered from user form of register object representation
     * @return returns String of generated hash
     */
    public String generateHash(String salt, String password) {

        return DigestUtils.sha256Hex(salt + password);
    }

}

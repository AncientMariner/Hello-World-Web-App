package org.xander.HWTO.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Test for security utilities method
 */
public class securityUtilsTest {

    @Test
    public void generateHashTest() {
        SecurityUtils securityUtils = new SecurityUtils();

        String password = "password";
        String salt = "salt";

        String expectedHash = DigestUtils.sha256Hex(salt + password);

        assertEquals(expectedHash, securityUtils.generateHash(salt, password));
    }

}

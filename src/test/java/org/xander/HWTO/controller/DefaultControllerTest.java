package org.xander.HWTO.controller;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Test for default controller.
 */
public class DefaultControllerTest {

    private DefaultController defaultController;

    @Before
    public void setUp() {
        defaultController = new DefaultController();
    }

    @Test
    public void defaultControllerTest() {
        String homepage = "redirect:/users/login";
        String actual = defaultController.homePage();

        assertEquals(homepage, actual);
    }
}

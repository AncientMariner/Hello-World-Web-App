package org.xander.HWTO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Default controller for redirection from default web server page.
 */
@Controller
public class DefaultController {
    /**
     * Redirection from default web server page.
     *
     * @return returns string of new address to go (some kind of start page)
     */
    @RequestMapping(value = "/")
    public String homePage() {
        return "redirect:/users/login";
    }
}

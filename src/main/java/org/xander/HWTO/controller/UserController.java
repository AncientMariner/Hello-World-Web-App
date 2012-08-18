package org.xander.HWTO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xander.HWTO.forms.UserLoginForm;
import org.xander.HWTO.forms.UserRegisterForm;
import org.xander.HWTO.model.entity.User;
import org.xander.HWTO.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * User controller for work with web pages.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    private static final String CURRENT_USER_SESSION_ATTRIBUTE_KEY = "currentUserKey";

    protected UserController() {
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Shows page with register object representation.
     *
     * @param model model that consist of pages
     * @return returns string of register page address
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(ModelMap model) {
        model.addAttribute("registerUser", new UserRegisterForm());
        return "register";
    }

    /**
     * Posts register page object representation.
     *
     * @param registerUserForm user that is taken from register object representation
     * @param result           binding result from spring
     * @return returns string of register page address if result has errors, otherwise redirects to login page
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("registerUser") UserRegisterForm registerUserForm, BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        } else {
            userService.addUser(registerUserForm);
            return "redirect:login";
        }

    }

    /**
     * Shows page with login object representation.
     *
     * @param model model that consist of pages
     * @return returns login page address
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUser(ModelMap model) {
        model.addAttribute("loginUser", new UserLoginForm());
        return "login";
    }

    /**
     * Posts login page object representation.
     *
     * @param loginUserForm login object representation
     * @param result        binding result from spring
     * @param model         model that consist of pages
     * @return returns hello page if login operation was successful, otherwise returns login page address
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@Valid @ModelAttribute("loginUser") UserLoginForm loginUserForm, BindingResult result, ModelMap model, HttpSession session) {

        if (!result.hasErrors()) {

            User user = userService.getByUserName(loginUserForm.getUsername());

            if (user == null) {
                model.addAttribute("userNotFoundMarker", true);
            } else {
                if (userService.checkUserPasswordEquality(user, loginUserForm.getPassword())) {

                    session.setAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY, user);

                    return "redirect:hello";
                } else {
                    model.addAttribute("passwordIsNotInvalidMarker", true);
                }
            }
        }

        return "login";
    }

    /**
     * Shows page with hello.
     *
     * @param model model that consist of pages
     * @return returns permitted page if user is not logged in, otherwise returns hello page
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloPage(ModelMap model, HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        if (user == null) {
            return "permittedPage";
        } else {
            model.addAttribute(user);
            return "hello";
        }
    }

    /**
     * Shows logout page.
     *
     * @param model model that consist of pages
     * @return returns login page if user was logged out
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(ModelMap model, HttpSession session) {
        session.removeAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        return "redirect:login";

    }
}

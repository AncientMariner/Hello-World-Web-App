package org.xander.HWTO.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.xander.HWTO.forms.UserRegisterForm;
import org.xander.HWTO.model.entity.User;
import org.xander.HWTO.service.UserService;

import javax.servlet.http.HttpSession;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test for user controller.
 */
@ContextConfiguration(locations = {"classpath:/org/xander/HWTO/service/applicationContext-service.xml", "classpath:/org/xander/HWTO/model/applicationContext-dao.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private UserController userController;

    private UserService userService;

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void registerUserGetTest() {
        ModelMap modelMap = new ModelMap();
        String registerGet = userController.registerUser(modelMap);

        assertEquals("register", registerGet);
    }

    @Test
    public void registerUserPostWithErrorsTest() {
        UserRegisterForm userRegisterForm = mock(UserRegisterForm.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);

        String registerURL = userController.registerUser(userRegisterForm, bindingResult);

        assertEquals(registerURL, "register");
        verify(userService, times(0)).addUser(userRegisterForm);
    }

    @Test
    public void registerUserPostWithoutErrorsTest() {
        UserRegisterForm userRegisterForm = mock(UserRegisterForm.class);
        BindingResult bindingResult = mock(BindingResult.class);

        String registerURL = userController.registerUser(userRegisterForm, bindingResult);

        assertEquals(registerURL, "redirect:login");
        verify(userService).addUser(userRegisterForm);
    }

    @Test
    public void loginGetTest() {
        ModelMap modelMap = mock(ModelMap.class);

        String login = userController.loginUser(modelMap);

        assertEquals("login", login);
    }

    @Test
    public void helloLoggedUserTest() {
        User user = mock(User.class);
        HttpSession session = mock(HttpSession.class);
        ModelMap modelMap = mock(ModelMap.class);

        when(session.getAttribute("currentUserKey")).thenReturn(user);

        String actualPage = userController.helloPage(modelMap, session);

        assertEquals("hello", actualPage);
        verify(modelMap).addAttribute(user);
    }

    @Test
    public void helloPermittedUserTest() {
        HttpSession session = mock(HttpSession.class);
        ModelMap modelMap = mock(ModelMap.class);

        when(session.getAttribute("currentUserKey")).thenReturn(null);

        String actualPage = userController.helloPage(modelMap, session);

        assertEquals("permittedPage", actualPage);
    }

    @Test
    public void logoutTest() {
        ModelMap modelMap = mock(ModelMap.class);
        HttpSession session = mock(HttpSession.class);

        String current_user_session_attribute_key = "currentUserKey";

        String actualLogout = userController.logoutPage(modelMap, session);

        assertEquals("redirect:login", actualLogout);
        verify(session).removeAttribute(eq(current_user_session_attribute_key));
    }
}

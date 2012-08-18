package org.xander.HWTO.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.xander.HWTO.forms.UserRegisterForm;
import org.xander.HWTO.model.dao.UserDao;
import org.xander.HWTO.model.entity.User;
import org.xander.HWTO.utils.SecurityUtils;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Test for transactional user service.
 */
@ContextConfiguration(locations = {"classpath:/org/xander/HWTO/service/applicationContext-service.xml", "classpath:/org/xander/HWTO/model/applicationContext-dao.xml"})
public class TransactionalUserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private SecurityUtils securityUtils;

    private UserService userService;

    @Before
    public void setUp() {
        initMocks(this);
        userService = new TransactionalUserService(userDao, securityUtils);
    }

    @Test
    public void getByUserName() {

        User user = mock(User.class);

        when(userDao.getByUserName(user.getUsername())).thenReturn(user);

        User result = userService.getByUserName(user.getUsername());

        assertEquals("User names are not equals", result, user);
        verify(userDao).getByUserName(user.getUsername());

    }

    @Test
    public void addUserTest() {
        UserRegisterForm userRegisterForm = mock(UserRegisterForm.class);

        String salt = "salt";
        String password = "password";

        when(userRegisterForm.getPassword()).thenReturn(password);
        when(securityUtils.generateSalt()).thenReturn(salt);

        userService.addUser(userRegisterForm);

        verify(securityUtils).generateSalt();
        verify(securityUtils).generateHash(eq(salt), eq(password));
        verify(userDao).saveOrUpdate(any(User.class));
    }

    @Test
    public void checkPasswordsEqualityTest() {
        User user = mock(User.class);

        String salt = "salt";
        String password = "password";

        when(user.getSalt()).thenReturn(salt);
        when(user.getPassword()).thenReturn("hash");
        when(securityUtils.generateHash(salt, password)).thenReturn("hash");

        boolean passwordsEquality = userService.checkUserPasswordEquality(user, password);

        assertTrue(passwordsEquality);
        verify(securityUtils).generateHash(eq(salt), eq(password));
    }

    @Test
    public void uniquenessNameCheckTest() {

        String userName = "userName";

        when(userDao.isUserWithGivenUserNameExists(userName)).thenReturn(true);

        userService.uniquenessNameCheck(userName);

        verify(userDao).isUserWithGivenUserNameExists(eq(userName));
    }
}

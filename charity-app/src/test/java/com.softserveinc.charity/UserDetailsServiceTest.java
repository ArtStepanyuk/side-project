package com.softserveinc.charity;


import com.softserveinc.charity.model.User;
//import com.softserveinc.charity.service.UserService;
import com.softserveinc.charity.service.security.UserDetailsService;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Ignore
public class UserDetailsServiceTest extends WebTest {

    //@Autowired
    //UserDetailsService userDetailsService;

//    @Autowired
//    UserService userService;

    //@Autowired
    //private SessionFactory sessionFactory;

    private User userExpected;

    @Before
    @Transactional
    @Rollback(false)
    public void init()
    {
        userExpected = new User();
        userExpected.setEmail("testAlibaba123@gmail.com");
        userExpected.setName("test");
        //userService.save(userExpected);
    }

    @Test
    @Transactional
    public void loadByUsernameSuccess()
    {
        //User userActual = userDetailsService.loadUserByUsername("testAlibaba123@gmail.com");
        //Assert.assertNotNull(userActual);
        //Assert.assertEquals(userActual.getName(), userExpected.getName());
    }

    @Transactional
    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsernameFail()
    {
        //userDetailsService.loadUserByUsername("testXXX");
    }

    @After
    @Transactional
    @Rollback(false)
    public void close()
    {
        //sessionFactory.getCurrentSession().delete(userExpected);
    }
}

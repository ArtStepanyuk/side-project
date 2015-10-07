package com.softserveinc.charity;


import com.softserveinc.charity.model.Address;
import com.softserveinc.charity.model.User;
import com.softserveinc.charity.repository.jpa.UserRepository;
import com.softserveinc.charity.security.service.UserDetailsService;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailsServiceTest extends AbstractWebIntegrationTest {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    private User userExpected;

    @Before
    @Transactional
    @Rollback(false)
    public void init()
    {
        userExpected = new User();
        userExpected.setEmail("testAlibaba123@gmail.com");
        userExpected.setName("test");
        userExpected.setAddress(new Address());
        userRepository.save(userExpected);
    }

    @Test
    @Transactional
    public void loadByUsernameSuccess()
    {
        User userActual = userDetailsService.loadUserByUsername("testAlibaba123@gmail.com");
        Assert.assertNotNull(userActual);
        Assert.assertEquals(userActual.getName(), userExpected.getName());
    }

    @Transactional
    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsernameFail()
    {
        userDetailsService.loadUserByUsername("testXXX");
    }

    @After
    @Transactional
    @Rollback(false)
    public void close()
    {
        userRepository.delete(userExpected);
    }
}

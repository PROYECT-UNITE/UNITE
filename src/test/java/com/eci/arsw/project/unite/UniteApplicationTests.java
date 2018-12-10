package com.eci.arsw.project.unite;

import com.eci.arsw.project.unite.beans.impl.InMemoryPersistence;
import com.eci.arsw.project.unite.model.User;
import com.eci.arsw.project.unite.services.UniteException;
import com.eci.arsw.project.unite.services.UniteServices;
import com.eci.arsw.project.unite.services.UniteServicesStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniteApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test(expected = UniteException.class)
    public void passwordTooShort() throws UniteException {
        User user = new User("SergioRt", "acd", "c@mail.com", "Sergio");
        User.passwordValid(user.getPassword());
    }

    @Test(expected = UniteException.class)
    public void passwordTooWeak() throws UniteException {
        User user = new User("SergioRt", "aaaaaa", "c@mail.com", "Sergio");
        User.passwordValid(user.getPassword());
    }

    @Test
    public void updatePassword() throws UniteException {
        User user = new User("SergioRt", "acdefghi", "c@mail.com", "Sergio");
        UniteServices us = new UniteServicesStub(new InMemoryPersistence());
        us.createAccount(user);
        Assert.assertTrue(us.grantAccess("SergioRt", "acdefghi"));
        us.updatePassword("SergioRt", "Sergio123456*");
        Assert.assertTrue(us.grantAccess("SergioRt", "Sergio123456*"));
        us.updatePassword("SergioRt", "otra");
        Assert.assertFalse(us.grantAccess("SergioRt", "Sergio123456*"));
    }


}

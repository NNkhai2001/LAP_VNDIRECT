package edu.hanoi.service;

import edu.hanoi.service.controller.UserRestServiceController;
import edu.hanoi.service.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringHnserviceApplication.class)
@WebAppConfiguration
@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
//        DirtiesContextBeforeModesTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class SpringHnserviceApplicationTests {
    @Autowired
    private UserRestServiceController userRestServiceController;
    @Test
    @WithMockUser(username = "admin",password = "123456",roles ={"USER"})
    public void contextLoads() {
       // List<User> users = userRestServiceController.listUser();
       // Assert.assertTrue(users.size() >0);
        List<User> users = userRestServiceController.listUser();
        Assert.assertTrue(users.size() >0);
        users.forEach(user -> {
            Assert.assertTrue(user.getAge() >15);
        });

    }
}

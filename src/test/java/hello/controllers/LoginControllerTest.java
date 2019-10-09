package hello.controllers;

import com.google.gson.Gson;
import hello.controllers.LoginController;
import hello.entities.User;
import hello.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoginControllerTest {


    @Autowired
    private TestEntityManager entityManager;
    private LoginController loginController;
    @Autowired
    private UserRepository userRepository;


    @Before
    public void setUp(){
        TestEnviroment.fullUser(entityManager);
//        loginController = new LoginController(userRepository);
    }
    @Test
    public void loginTest(){
        User user = new User();
        user.setUsername("fedor");
        user.setPassword("123");
        Gson gson = new Gson();
        System.out.println(gson.toJson(user));
        String actual = loginController.login(gson.toJson(user));
        System.out.println(actual);
    }
}

package hello.controllers;

import hello.controllers.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    LoginController loginController;
    private String json = "{\n" +
            "  \"username:\": 123,\n" +
            "  \"password:\": 42\n" +
            "}";


    @Test
    public void loginTest(){
        String actual = loginController.login(json);
        System.out.println(actual);
    }
}

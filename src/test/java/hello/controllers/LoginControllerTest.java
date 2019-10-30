//package hello.controllers;
//
//import com.google.gson.Gson;
//import hello.entities.User;
//import hello.repository.UserRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@ContextConfiguration(classes = TestConfig.class)
//public class LoginControllerTest {
//
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Autowired
//    UserController userController;
//
//
//    @Before
//    public void setUp(){
//        TestEnviroment.fullUser(entityManager);
//    }
//
//    @Test
//    public void loginTest(){
//        User user = new User();
//        user.setUsername("maxim");
//        user.setPassword("1234");
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(user));
//        String actual = userController.login(gson.toJson(user));
//        System.out.println(actual);
//    }
//}

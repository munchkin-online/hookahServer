//package hello.controllers;
//
//import hello.entities.User;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//public class TestEnviroment {
//
//
//    public static void fullUser(TestEntityManager entityManager){
//        User user = new User();
//        //user.setId(1);
//        user.setRole("master");
//        user.setEmail("asd");
//        user.setPassword("123");
//        user.setUsername("fedor");
//        entityManager.persist(user);
//
//        User user1 = new User();
//        //user1.setId(2);
//        user1.setRole("guest");
//        user1.setEmail("134");
//        user1.setPassword("gfh");
//        user1.setUsername("ivan");
//        entityManager.persist(user1);
//
//        User user3 = new User();
//        //user3.setId(3);
//        user.setRole("dfdsf");
//        user.setEmail("asd");
//        user.setPassword("1234");
//        user.setUsername("maxim");
//        entityManager.persist(user3);
//
//        entityManager.flush();
//    }
//}

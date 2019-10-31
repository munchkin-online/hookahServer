package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.User;
import hello.helper.Status;
import hello.helper.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hello.repository.UserRepository;


@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public String login(@RequestBody String loginJson){
        log.info("login request, info={}",loginJson);
        return userService.loginCheck(loginJson);
    }

    @PostMapping("/user/registry")
    public String registration(@RequestBody String registrationJson) {
        log.info("registration request, info={}",registrationJson);
        return userService.checkRegistration(registrationJson);
    }

    @PostMapping("/user/info")
    public String info(@RequestBody String infoJson){
        log.info("user info request, info={}",infoJson);
        return userService.getUserInfo(infoJson);
    }

    @PostMapping("/user/status")
    public String status(@RequestBody String statusJson){
        return null;
    }
}

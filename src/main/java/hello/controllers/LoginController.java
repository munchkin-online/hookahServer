package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.User;
import hello.helper.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hello.repository.UserRepository;


@RestController
@Slf4j
public class LoginController {

    //@Autowired
    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public String login(@RequestBody String loginJson){
        log.info("login request, info={}",loginJson);
        return loginCheck(loginJson);
    }

    private String loginCheck(String loginJson){

        Gson gson = new Gson();
        User userActual = gson.fromJson(loginJson,User.class);

        User userInDB = userRepository.findByUsername(userActual.getUsername());//getting user in database with this login

        String message = "something go wrong";
        Integer status = Status.BAD_STATUS.getStatusCode();
        JsonObject jsonObject = new JsonObject();
        if(userInDB!=null&&userActual.getPassword().equals(userInDB.getPassword())){ //checking user from db
            message = "Login successful";
            status = Status.OK_STATUS.getStatusCode();
            jsonObject.addProperty("role", userInDB.getRole());
            log.info("login right");
        }
        if(userInDB==null) {
            message = "user does not exist, create account first";
            status = Status.BAD_STATUS.getStatusCode();
            log.info("user {} does not exist",userActual.getUsername());
        }else if(!userInDB.getPassword().equals(userActual.getPassword())){
            message = "wrong login/password";
            status = Status.BAD_STATUS.getStatusCode();
            log.info("wrong password for user {}",userActual.getUsername());
        }

        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);
        String jsonToClient = jsonObject.toString();

        log.info("return to client={}", jsonToClient);
        return jsonToClient;
    }
}

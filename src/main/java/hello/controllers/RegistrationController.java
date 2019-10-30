package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.User;
import hello.helper.Status;
import hello.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registry")
    public String registry(@RequestBody String registrationJson) {
        log.info("registration request, info={}",registrationJson);
        return checkRegister(registrationJson);
    }
    private String checkRegister(String registrationJson){
        Gson gson = new Gson();
        User user = gson.fromJson(registrationJson,User.class);
        User userInDb = userRepository.findByUsername(user.getUsername());
        JsonObject jsonObject = new JsonObject();
        String message;
        Integer status;
        if(userInDb == null) {
            log.info("add user to database");
            user.setRole("new");
            userRepository.save(user);
            message = "User registered";
            status = Status.OK_STATUS.getStatusCode();
        }else{
            message = "User already exist";
            status = Status.BAD_STATUS.getStatusCode();
        }

        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);
        String jsonToClient = jsonObject.toString();

        log.info("return to client={}", jsonToClient);
        return jsonToClient;
    }
}

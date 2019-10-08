package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import hello.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import hello.repository.UserRepository;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(String loginJson){
        Gson gson = new Gson();
        User userActual = gson.fromJson(loginJson,User.class);

        User userInDB = userRepository.findByUsername(loginJson);

        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String message;
        JsonObject jsonObject = new JsonObject();
        if(userInDB!=null&&userActual.getPassword().equals(userInDB.getPassword())){
            jsonObject.addProperty("status","right");
            jsonObject.addProperty("role:",userInDB.getRole());
            message = gson.toJson(jsonObject);
            return message;
        }
        jsonObject.addProperty("status","wrong");
        message = gson.toJson(jsonObject);
        return message;
    }

}

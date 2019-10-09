package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import hello.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import hello.repository.UserRepository;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public String login(String loginJson){
        Gson gson = new Gson();
        User userActual = gson.fromJson(loginJson,User.class);

        System.out.println(userRepository.findAll().toString());

        User userInDB = userRepository.findByUsername(userActual.getUsername());

        Iterable<User> userIterable = userRepository.findAll();
        if(userIterable.iterator().hasNext()){
            System.out.println(userIterable.iterator().next().getUsername());
        }
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String message;
        JsonObject jsonObject = new JsonObject();
        if(userInDB!=null&&userActual.getPassword().equals(userInDB.getPassword())){
            jsonObject.addProperty("status","right");
            jsonObject.addProperty("role:", userInDB.getRole());
            System.out.println(gson.toJson(jsonObject));
            message = gson.toJson(jsonObject);
            return message;
        }
        jsonObject.addProperty("status","wrong");
        message = gson.toJson(jsonObject);
        return message;
    }

}

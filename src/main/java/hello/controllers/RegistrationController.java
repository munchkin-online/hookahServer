package hello.controllers;

import com.google.gson.Gson;
import hello.entities.User;
import hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registry")
    public String registry(@RequestBody String registryJson) {
        Gson gson = new Gson();
        System.out.println(registryJson);
        User user = gson.fromJson(registryJson,User.class);
        User userInDb = userRepository.findByUsername(user.getUsername());
        if(userInDb == null) {
            userRepository.save(user);
            return "ok";
        }
        else return "not ok";
    }
}

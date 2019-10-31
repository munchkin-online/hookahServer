package hello.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import hello.entities.User;
import hello.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Gson gson = new Gson();

    public String checkRegistration(String registrationJson){
        User user = gson.fromJson(registrationJson,User.class);
        User userInDb = userRepository.findByUsername(user.getUsername());
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

        return getJsonString(message, status);
    }


    public String loginCheck(String loginJson){

        User user = gson.fromJson(loginJson,User.class);
        String message;
        Integer status;
        if(userRepository.existsUserByUsernameAndPassword(user.getUsername(),user.getPassword())){ //checking user from db
            message = "Login successful";
            user = userRepository.findByUsername(user.getUsername());
            status = Status.OK_STATUS.getStatusCode();
            log.info("login right");
            return getJsonStringWithUser(user, message, status);
        } else {
            message = "wrong login/password or user does not exist";
            status = Status.BAD_STATUS.getStatusCode();
            log.info("wrong password for user {}",user.getUsername());
            return getJsonString(message, status);
        }
    }

    private String getJsonStringWithUser(User user, String message, Integer status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);

        jsonObject.add("user",gson.toJsonTree(user));
        String jsonToClient = jsonObject.toString();
        log.info("return to client={}", jsonToClient);

        return jsonToClient;
    }


    public String getUserInfo(String infoJson){
        User user = gson.fromJson(infoJson,User.class);
        String message = "user does not exist";
        Integer status = Status.BAD_STATUS.getStatusCode();
        if(userRepository.existsUserByUsername(user.getUsername())){
            user = userRepository.findByUsername(user.getUsername());
            status = Status.OK_STATUS.getStatusCode();
            message = "user exists";
        }
        return getJsonStringWithUser(user, message, status);
    }

    public String changeUserRole(String roleJson){
        User userFromClient = gson.fromJson(roleJson,User.class);
        String message = "user does not exist";
        Integer status = Status.BAD_STATUS.getStatusCode();
        if(userRepository.existsUserByUsername(userFromClient.getUsername())){
            User user = userRepository.findByUsername(userFromClient.getUsername());
            user.setRole(userFromClient.getRole());
            userRepository.save(user);
            status = Status.OK_STATUS.getStatusCode();
            message = "role has been updated";
        }
        return getJsonString(message, status);
    }

    private String getJsonString(String message, Integer status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);
        String jsonToClient = jsonObject.toString();

        log.info("return to client={}", jsonToClient);
        return jsonToClient;
    }
}

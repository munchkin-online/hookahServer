package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.Tobacco;
import hello.helper.Status;
import hello.repository.TobaccoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AddTobaccoController {

    private TobaccoRepository tobaccoRepository;
    @Autowired
    public AddTobaccoController(TobaccoRepository tobaccoRepository){
        this.tobaccoRepository = tobaccoRepository;
    }

    @PostMapping("/tobacco")
    public String tobacco(@RequestBody String loginJson){
        log.info("tobacco request, info={}",loginJson);
        return addTobacco(loginJson);
    }

    private String addTobacco(String loginJson){

        Gson gson = new Gson();

        Tobacco tobaccoActual = gson.fromJson(loginJson,Tobacco.class);
        Tobacco tobaccoInDB = tobaccoRepository.findByLabel(tobaccoActual.getLabel());


        String message;
        int status;

        if(tobaccoInDB == null) {
            log.info("add tobacco to database");
            tobaccoRepository.save(tobaccoActual);
            status = Status.OK_STATUS.getStatusCode();
            message = "Add Tobacco";
        }else{
            status = Status.BAD_STATUS.getStatusCode();
            message = "Tobacco already exist";
        }


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message", message);

        String jsonToClient = jsonObject.toString();
        log.info("return to client={}", jsonToClient);
        return jsonToClient;
    }
}

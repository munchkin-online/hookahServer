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
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TobaccoController {

    private TobaccoRepository tobaccoRepository;
    private Gson gson = new Gson();
    @Autowired
    public TobaccoController(TobaccoRepository tobaccoRepository){
        this.tobaccoRepository = tobaccoRepository;
    }

    @PostMapping("/tobacco/add")
    public String add(@RequestBody String tobaccoJson){
        log.info("tobacco request, info={}",tobaccoJson);
        return addTobacco(tobaccoJson);
    }


    @PostMapping("/tobacco/list")
    public String getListOfTobacco(){
        log.info("request to get all tobaccos");
        List<Tobacco> tobaccoList = new ArrayList<>();
        Iterable<Tobacco> iterable = tobaccoRepository.findAll();
        iterable.forEach(tobaccoList::add);
        return getTobbacoList();
    }


    private String getTobbacoList(){
        List<Tobacco> tobaccoList = new ArrayList<>();
        Iterable<Tobacco> iterable = tobaccoRepository.findAll();
        iterable.forEach(tobaccoList::add);
        return gson.toJson(tobaccoList);
    }
    private String addTobacco(String tobaccoJson){

        Tobacco tobaccoActual = gson.fromJson(tobaccoJson,Tobacco.class);
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

package hello.helper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.Order;
import hello.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private Gson gson = new Gson();
    private JsonObject jsonObject = new JsonObject();

    public String addOrder(String orderJson){
        Order order = gson.fromJson(orderJson, Order.class);
        String message;
        int status;
        try{
            order.setStatus("new");
            orderRepository.save(order);
            log.info("add order to database");
            message = "add order to database";
            status = Status.OK_STATUS.getStatusCode();
        } catch (Exception e){
            log.info("added order to database - Failed, Error = " + e);
            message = "added order to database - Failed, Error = " + e;
            status = Status.BAD_STATUS.getStatusCode();
        }
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);
        String jsonToClient = jsonObject.toString();
        log.info("return to client={", jsonToClient + "}");
        return jsonToClient;
    }
}

package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.Order;
import hello.helper.OrderService;
import hello.helper.Status;
import hello.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    private OrderRepository orderRepository;
    //OrderService orderService;
    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    @PostMapping("/order/add")
    public String add(@RequestBody String orderJson){
        log.info("order request info = {" + orderJson + "}");
        return addOrder(orderJson);
    }

    @PostMapping("/order/list")
    public String getListOfOrders(){
        log.info("request to get all tobaccos");
//        TODO: возвращать все заказы со статусом не в работе
        return "getList";
    }
    // new - заказ создан; work - заказ в работе; end - работа с заказом выполнена

    @PostMapping("/order/info")
    public String info(@RequestBody String orderJson){
        log.info("request to get info about order, info = {" + orderJson + "}");
//        TODO: возвращать всю информацию о конкретном заказе
        return "info";
    }

    @PostMapping("/order/close")
    public String closeOrder(@RequestBody String orderJson){
        log.info("request to close order, info = {" + orderJson + "}");
//        TODO: сделать закрытие заказа - удаление из таблички заказы и возможно перевод в историю
        return "closeOrder";
    }

    private String addOrder(String orderJson){
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
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

package hello.controllers;

import hello.helper.OrderService;
import hello.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {


    @Autowired
    OrderService orderService;

    @PostMapping("/order/add")
    public String add(@RequestBody String orderJson){
        log.info("order request info = {" + orderJson + "}");
        return "addOrder";
    }

    @PostMapping("/order/list")
    public String getListOfOrders(){
        log.info("request to get all tobaccos");
//        TODO: возвращать все заказы со статусом не в работе
        return "getList";
    }

    @PostMapping("/order/info")
    public String info(@RequestBody String orderJson){
        log.info("request to get info about order, info = {" + orderJson + "}");
//        TODO: возвращать всю информацию о конкретном заказе
        return "info";
    }

    @PostMapping("/order/close")
    public String closeOrder(@RequestBody String orderJson){
        log.info("request to close order, info = {" + "}");
//        TODO: сделать закрытие заказа - удаление из таблички заказы и возможно перевод в историю
        return "closeOrder";
    }

}

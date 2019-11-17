package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.Order;
import hello.entities.Tobacco;
import hello.entities.Zabiv;
import hello.helper.Status;
import hello.repository.OrderRepository;
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
public class OrderController {

    private OrderRepository orderRepository;
    private TobaccoRepository tobaccoRepository;
    //OrderService orderService;
    @Autowired
    public OrderController(OrderRepository orderRepository, TobaccoRepository tobaccoRepository){
        this.orderRepository = orderRepository;
        this.tobaccoRepository = tobaccoRepository;
    }

    private Gson gson = new Gson();


    @PostMapping("/order/add")
    public String add(@RequestBody String orderJson){
        log.info("order request info = {" + orderJson + "}");
        return addOrder(orderJson);
    }

    @PostMapping("/order/list")
    public String getListOfOrders(){
        log.info("request to get all tobaccos");
//        TODO: возвращать все заказы со статусом не в работе
        return getOrderList();
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
        JsonObject jsonObject = new JsonObject();
        Order order = gson.fromJson(orderJson, Order.class);
        //получаем заказ с забивом из 3 табаков
        String message;
        int status;
        try{
            order.setStatus("new");
            List<Zabiv> listZabiv = order.getOrder();
            // узнаем сколько табаков в листе и их id записываем в класс:
            for (int i = 0; i < listZabiv.size(); i ++){ //для каждой забивки (помним номер)
                Zabiv zabiv = listZabiv.get(i); //достаем забивку из листа
                List<Tobacco> listTobacco = zabiv.getFlavours(); //достаем из забивки лист табаков
                if (listTobacco.size() == 1){ //проверяем сколько табаков в забивке
                    log.info("listTobacco.size() == 1");
                    zabiv.setId1(listTobacco.get(0).getId()); //если табак есть, сохраняем его номер
                    zabiv.setId2(0);   //если табака нет - сохраняем 0
                    zabiv.setId3(0);
                } else if (listTobacco.size() == 2) {
                        log.info("listTobacco.size() == 2");
                        zabiv.setId1(listTobacco.get(0).getId());
                        zabiv.setId2(listTobacco.get(1).getId());
                        zabiv.setId3(0);
                    }
                    else if (listTobacco.size() == 3) {
                        log.info("listTobacco.size() == 3");
                            zabiv.setId1(listTobacco.get(0).getId());
                            zabiv.setId2(listTobacco.get(1).getId());
                            zabiv.setId3(listTobacco.get(2).getId());
                    } else{
                    log.info("listTobacco.size() == 0");
                    zabiv.setId1(0);
                    zabiv.setId2(0);
                    zabiv.setId3(0);
                }
                listZabiv.set(i,zabiv);
            }
            order.setOrder(listZabiv);

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

    private String getOrderList(){
        List<Order> orderList = new ArrayList<>();
        Iterable<Order> iterable = orderRepository.findAll();
        iterable.forEach(orderList::add);
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            List<Zabiv> listZabiv = order.getOrder();
            for (int j = 0; j < listZabiv.size(); j++) {
                Zabiv zabiv = listZabiv.get(j);
                List<Tobacco> listTobacco = zabiv.getFlavours();
                if (zabiv.getId1()>0){
                    if (tobaccoRepository.findById(zabiv.getId1()).isPresent()){
                        Tobacco tobacco = tobaccoRepository.findById(zabiv.getId1()).get();
                        listTobacco.add(tobacco);
                    }
                }
                if (zabiv.getId2()>0){
                    if (tobaccoRepository.findById(zabiv.getId2()).isPresent()){
                        Tobacco tobacco = tobaccoRepository.findById(zabiv.getId2()).get();
                        listTobacco.add(tobacco);
                    }
                }
                if (zabiv.getId3()>0){
                    if (tobaccoRepository.findById(zabiv.getId3()).isPresent()){
                        Tobacco tobacco = tobaccoRepository.findById(zabiv.getId3()).get();
                        listTobacco.add(tobacco);
                }
                }
                zabiv.setFlavours(listTobacco);
                listZabiv.set(j,zabiv);
            }
            order.setOrder(listZabiv);
            orderList.set(i,order);
        }
        Order order1 = orderList.get(0);//никита попросил возвращать просто класс
        return gson.toJson(order1);
    }

}

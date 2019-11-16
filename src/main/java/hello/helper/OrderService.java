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
        String jsonToClient = "";
        return jsonToClient;
    }
}

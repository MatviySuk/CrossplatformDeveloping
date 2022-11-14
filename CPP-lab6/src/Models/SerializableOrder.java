package Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SerializableOrder implements Serializable {
    public int orderID;
    public List<Pizza> pizzas = new ArrayList<>();
    public String deliveryTime;
    public boolean IsDelivered = false;

    public SerializableOrder(Order order) {
        this.orderID = order.getOrderID();
        this.pizzas = order.getPizzas();
        this.deliveryTime = order.getDeliveryTime().toString();
        this.IsDelivered = order.isDelivered();
    }
}

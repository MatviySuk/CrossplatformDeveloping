package Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Order implements Serializable {
    private static int orderCounter = 1;

    private int orderID;
    private List<Pizza> pizzas = new ArrayList<>();
    private LocalDateTime deliveryTime;

    private boolean IsDelivered = false;

    public Order(List<Pizza> pizzas, LocalDateTime deliveryTime) {
        this.orderID = orderCounter++;
        this.pizzas = pizzas;
        this.deliveryTime = deliveryTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public boolean isDelivered() {
        return IsDelivered;
    }

    public void setDelivered(boolean delivered) {
        IsDelivered = delivered;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public int getOrderID() {
        return orderID;
    }

    @Override
    public String toString() {
        return "OrderID: " + orderID + ". Delivery time: " + deliveryTime;
    }
}


package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private String address;
    private List<Order> orders = new ArrayList<>();

    public Customer(String address) {
        this.address = address;
    }

    public Customer(String address, List<Order> orders) {
        this.address = address;
        this.orders = orders;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public void AddOrder(Order order) {
        orders.add(order);
    }

    public List<Pizza> getAllPizzas() {
        return orders.stream().flatMap(
                order -> order.getPizzas().stream()
        ).toList();
    }

    @Override
    public String toString() {
        return address;
    }
}

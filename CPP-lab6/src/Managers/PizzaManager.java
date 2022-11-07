package Managers;

import Models.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class PizzaManager {
    private final PizzaPlace pizzaPlace;

    public PizzaManager(PizzaPlace pizzaPlace) {
        this.pizzaPlace = pizzaPlace;
    }

    // TASK 1
    public List<Order> SortOrderByDeliveryTime() {
        return pizzaPlace.getAllOrders()
                .stream()
                .sorted(new OrderComparator())
                .toList();
    }

    // TASK 2
    // Return addresses of all customers who ordered 2 and more pizzas
    public List<String> GetCustomersAddressTwoAndMore() {
        return pizzaPlace.getAllCustomers().stream().map(cus ->
            (
                    cus.getOrders().stream().map(
                            order -> order.getPizzas().size()
                    ).reduce(0, Integer::sum) > 1
            ) ? cus.getAddress()
              : null
            )
                .filter(Objects::nonNull)
                .toList();
    }

    // TASK 3

    public int CountOrderedPizzaByName(String name) {
        return pizzaPlace.getAllPizzas().stream().filter(
                pizza -> pizza.getName().equalsIgnoreCase(name)
        ).toList().size();
    }

    // TASK 4
    public int CountMostOrderByCustomerFor(Pizza pizza) {
        return pizzaPlace.getAllCustomers().stream().mapToInt(
                customer -> customer.getAllPizzas().stream().filter(
                        cPizza -> Objects.equals(cPizza.getName(), pizza.getName())
                ).toList().size()).max().orElse(0);
    }

    // TASK 5
    public Map<Pizza, List<Customer>> GetCustomerListForAllPizzas() {
        Map<Pizza, List<Customer>> pizzaCustomers = new HashMap<Pizza, List<Customer>>();

        Pizza.AllPizzas().forEach(pizza -> {
            pizzaCustomers.put(pizza, pizzaPlace.getAllCustomers().stream().filter(
                            customer -> customer.getAllPizzas().stream().map(
                                    Pizza::getName
                            ).toList().contains(pizza.getName())
                    ).toList());
        });

        return pizzaCustomers;
    }

    // TASK 6
    public Map<Order, Long> GetAllNotDeliveredOrders() {
        Map<Order, Long> orderMap = new HashMap<Order, Long>();

        pizzaPlace.getAllOrders().stream().filter(o -> !o.isDelivered()).forEach(order -> {
            orderMap.put(
                    order,
                    Duration.between(order.getDeliveryTime(), LocalDateTime.now()).toMinutes()
            );
        });

        return orderMap;
    }
}

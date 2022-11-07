package Models;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PizzaPlace {
    final private String fileName = "placeData.txt";
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private List<Customer> allCustomers = new ArrayList<>();

    public PizzaPlace() {
        try (FileReader reader = new FileReader(fileName)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] allData = tmp.split(",");
                List<Order> orders = new ArrayList<>();

                for (int i = 1; i < allData.length; i++) {
                    String data = allData[i];
                    String[] orderData = data.split("~");
                    String[] pizzaNames = orderData[0].split("/");

                    orders.add(new Order(
                            Arrays.stream(pizzaNames).map(name -> Pizza.GetPizzaByName(name)).toList(),
                            LocalDateTime.parse(orderData[1], formatter)
                    ));
                }

                allCustomers.add(new Customer(allData[0], orders));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void addOrderForCustomer(Order order, Customer customer) {
        if (allCustomers.contains(customer)) {
            int index = allCustomers.indexOf(customer);

            allCustomers.get(index).AddOrder(order);
        } else {
            customer.AddOrder(order);
            allCustomers.add(customer);
        }
    }

    public void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    public List<Order> getAllOrders() {
        return allCustomers.stream().flatMap(customer -> customer.getOrders().stream()).toList();
    }

    public List<Pizza> getAllPizzas() {
        return allCustomers.stream().flatMap(
                customer -> customer.getOrders().stream().flatMap(
                        order -> order.getPizzas().stream()
                )
        ).toList();
    }
}

package Models;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PizzaPlace implements Serializable {
    final static private String fileName = "placeData.txt";
    final static private String serializeFile = "pizzaData.ser";
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private List<Customer> allCustomers = new ArrayList<>();

    public PizzaPlace() { }

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

    public void Serialize() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(serializeFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static PizzaPlace ReadFromTextFile() {
        PizzaPlace pizzaPlace = new PizzaPlace();

        try (FileReader reader = new FileReader(PizzaPlace.fileName)) {
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
                            LocalDateTime.parse(orderData[1], PizzaPlace.formatter)
                    ));
                }

                pizzaPlace.addCustomer(new Customer(allData[0], orders));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return pizzaPlace;
    }

    public static PizzaPlace Deserialize() {
        PizzaPlace pizzaPlace = new PizzaPlace();

        try {
            FileInputStream fileInputStream = new FileInputStream(PizzaPlace.serializeFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            pizzaPlace = (PizzaPlace) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

        return pizzaPlace;
    }
}

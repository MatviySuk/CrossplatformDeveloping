package Models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pizza implements Serializable {
    private String name;
    private double weight;
    private double price;
    private ArrayList<String> ingradients;

    public Pizza(String name, double weight, double price, ArrayList<String> ingradients) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.ingradients = ingradients;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<String> getIngradients() {
        return ingradients;
    }

    public static Pizza Margarita = new Pizza(
            "Margarita",
            0.450,
            100.0,
            new ArrayList<String>(List.of("Cheese", "Tomato"))
    );

    public static Pizza Diablo = new Pizza(
            "Diablo",
            0.650,
            174.0,
            new ArrayList<String>(List.of("Chili Papper", "Tomato", "Hot Salami"))
    );

    public static Pizza FourCheeses = new Pizza(
            "FourCheeses",
            0.700,
            199.0,
            new ArrayList<String>(List.of("Tomato", "Mozzarella", "Parmesan", "Dor Blue", "Cheddar"))
    );

    public static List<Pizza> AllPizzas() {
        return new ArrayList<>(List.of(
                Pizza.Margarita,
                Pizza.Diablo,
                Pizza.FourCheeses
        ));
    }

    public static Pizza GetPizzaByName(String name) {
        return Pizza.AllPizzas().stream().filter(pizza -> pizza.getName().equals(name)).toList().get(0);
    }

    @Override
    public String toString() {
        return name;
    }
}

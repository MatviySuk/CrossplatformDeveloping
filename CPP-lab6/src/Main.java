import Managers.PizzaManager;
import Models.Customer;
import Models.Order;
import Models.Pizza;
import Models.PizzaPlace;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isActive = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        PizzaPlace pizzaPlace = new PizzaPlace();
        PizzaManager pizzaManager = new PizzaManager(pizzaPlace);

        do {
            System.out.println(
                    "1. Відсортувати всі замовлення за часом доставки.\n"
                    + "2. Створити список адресів для користувачів, що замовили більше ніж 2 піцци.\n"
                    + "3. Перевірити, скільки користувачів замовили піццу з заданою назвою.\n"
                    + "4. Знайти найбільшу кількість піц, замовлених користувачем серед запропонованого переліку піц.\n"
                    + "5. Створити колекцію з переліком піц та списками їх замовників.\n"
                    + "6. Створити список не виконаних замовлень на біжучий час, та вказати час перетермінування.\n"
                    + "7. Зчитати із текстового файлу.\n"
                    + "8. Serialize.\n"
                    + "9. Deserialize.\n"
                    + "10. Завершити роботу програми."
            );

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    pizzaManager.SortOrderByDeliveryTime().forEach(order -> {
                        System.out.println(order);
                    });
                    break;
                case 2:
                    System.out.println(
                        pizzaManager.GetCustomersAddressTwoAndMore()
                    );
                    break;
                case 3:
                    Scanner nameScanner = new Scanner(System.in);
                    System.out.println("Enter pizza name you would like to count:");
                    String productName = nameScanner.nextLine();

                    System.out.println(
                            pizzaManager.CountOrderedPizzaByName(productName)
                    );
                    break;
                case 4:
                    Scanner pizzaScanner = new Scanner(System.in);
                    System.out.println("Choose Pizza to count: ");

                    for (int i = 0; i < Pizza.AllPizzas().size(); i++) {
                        System.out.println(i + ". " + Pizza.AllPizzas().get(i).getName());
                    }

                    int chosenIndex = pizzaScanner.nextInt();

                    if (chosenIndex <= Pizza.AllPizzas().size()) {
                        Pizza selectedPizza = Pizza.AllPizzas().get(chosenIndex);

                        System.out.println(
                                "The most " + selectedPizza.getName()
                                        + " ordered by one customer: "
                                        + pizzaManager.CountMostOrderByCustomerFor(selectedPizza)
                        );
                    } else {
                        System.out.println("You chose wrong number!");
                    }

                    break;
                case 5:
                    System.out.println(
                        pizzaManager.GetCustomerListForAllPizzas()
                    );
                    break;
                case 6:
                    pizzaManager.GetAllNotDeliveredOrders().forEach((k, v) -> {
                        System.out.println(
                                "OrderID: " + k.getOrderID()
                                + (v <= 0
                                        ? " is not delayed."
                                        : " delayed for minutes: " + v
                                )
                        );
                    });
                    break;
                case 7:
                    pizzaPlace = PizzaPlace.ReadFromTextFile();
                    pizzaManager = new PizzaManager(pizzaPlace);
                    break;
                case 8:
                    pizzaPlace.Serialize();
                    break;
                case 9:
                    pizzaPlace = PizzaPlace.Deserialize();
                    pizzaManager = new PizzaManager(pizzaPlace);
                    break;
                case 10:
                    System.out.println("Роботу програми завершено!");
                    isActive = false;
                    break;
                default:
                    System.out.println("Введено неправильну цифру");
            }

            System.out.println("\n");
        } while (isActive);
    }
}
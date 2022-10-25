import Product.Manager.ProductShop;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean isActive = true;
        ProductShop productShop = new ProductShop("firstFile.txt");


        do {
            System.out.println(
                    "1. Здійснити переоцінку всіх продуктів на 10 відсотків, кінцева дата споживання яких є через 3 дні від біжучої дати.\n"
                    + "2. Видрукувати таблицю, в якій в першій колонці розташовані кінцеві дати,"
                    + " в другій – колекція всіх продуктів з заданою кінцевою датою.\n"
                    + "3. Вилучити з попередньої таблиці всі продукти з заданою назвою.\n"
                    + "4. З 2 різних файлів зчитати 2 вихідні набори інформації про продукти.\n"
                    + "5. Визначити спільну колекцію унікальних за назвою продуктів, які є тільки в першому,"
                    + " або тільки в другому наборі, вилучивши з неї елементи дата виготовлення яких є не в біжучому році.\n"
                    + "6. Вивести дані про всі продукти.\n"
                    + "7. Завершити роботу програми."
            );

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    productShop.makeTenPercentDiscount();
                    break;
                case 2:
                    productShop.outputProductsGroupedByConsumeDate();
                    break;
                case 3:
                    Scanner nameScanner = new Scanner(System.in);
                    System.out.println("Enter product name you would like to delete:");
                    String productName = nameScanner.nextLine();
                    productShop.deleteProductWithName(productName);
                    break;
                case 4:
                    productShop = new ProductShop("firstFile.txt", "secondFile.txt");
                    break;
                case 5:
                    productShop.outputUniqueProductsOfPrevYears();
                    break;
                case 6:
                    System.out.println("Дані із таблиці із продуктами:");
                    productShop.outputAllProducts();
                    break;
                case 7:
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
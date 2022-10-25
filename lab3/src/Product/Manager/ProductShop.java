package Product.Manager;
import Product.Product;

import javax.sound.sampled.Port;
import java.time.LocalDate;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;


public class ProductShop {
    private ArrayList<Product> products = new ArrayList<>();

    private ArrayList<Product> firstProducts = new ArrayList<>();
    private ArrayList<Product> secProducts = new ArrayList<>();


    public ArrayList<Product> getProducts() {
        return products;
    }

    public ProductShop(String firstFile, String secondFile) {
        fetchProductsFromFile(firstFile, firstProducts);
        fetchProductsFromFile(secondFile, secProducts);
    }

    public ProductShop(String firstFile) {
        fetchProductsFromFile(firstFile, products);
    }

    private void fetchProductsFromFile(String fileName, List<Product> products) {
        try (FileReader reader = new FileReader(fileName)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] buffer = tmp.split(" ");
                products.add(new Product(buffer[0], buffer[1], buffer[2], buffer[3]));
                System.out.println(products.get(products.size() - 1));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void outputAllProducts() {
        if (products != null) {
            OutputManager.outputProducts(products);
        } else {
            System.out.println("Продукти відсутні!");
        }
    }

    public void makeTenPercentDiscount() {
        int todayDay = LocalDate.now().getDayOfYear();

        for (int i = 0; i < products.size(); i++) {
            int dayResult = products.get(i).getConsumeByDate().getDayOfYear() - todayDay;
            if ( dayResult <= 3 && dayResult >= 0) {
                products.set(i, new Product(
                        products.get(i).getName(),
                        products.get(i).getProducedDate(),
                        products.get(i).getConsumeByDate(),
                        products.get(i).getPrice() * 0.9
                        )
                );
            }
        }
    }

    public void deleteProductWithName(String prodName) {
        int deletedCount = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(prodName)) {
                products.remove(i);
                deletedCount++;
            }
        }

        if (deletedCount > 0) {
            System.out.println(
                    String.format("%d products deleted with name: %s", deletedCount, prodName)
            );
        } else {
            System.out.println(
                    String.format(
                            "There is not products to delete with name: %s", prodName
                    )
            );
        }
    }

    public void outputProductsGroupedByConsumeDate() {
        HashMap<LocalDate, List<Product>> groupedByConsume = new HashMap<LocalDate, List<Product>>();

        products.stream().map(product -> product.getConsumeByDate()).forEach(localDate ->
                groupedByConsume.put(localDate, products.stream().filter(product ->
                        product.getConsumeByDate().isEqual(localDate)
                    ).toList()
                )
        );

        groupedByConsume.forEach((localDate, products1) ->
                System.out.println(String.format("%-25s%-25s", localDate, products1))
        );
    }

    public void outputUniqueProductsOfPrevYears() {
        List<Product> firstSorted = new ArrayList<Product>(firstProducts.stream().filter(product -> !secProducts.stream().map(
                product1 -> product1.getName()).toList().contains(product.getName())
        ).toList());


        List<Product> secSorted = secProducts.stream().filter(product -> !firstProducts.stream().map(
                product1 -> product1.getName()).toList().contains(product.getName())
        ).toList();

        if (secSorted.size() > 0)
            firstSorted.addAll(secSorted);

        OutputManager.outputProducts(firstSorted.stream().filter(
                product -> (product.getProducedDate().getYear() < LocalDate.now().getYear())
            ).toList()
        );
    }
}

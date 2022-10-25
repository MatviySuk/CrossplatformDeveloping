package Product.Manager;

import Product.Product;

import java.util.List;

public class OutputManager {
    public static void outputProducts(List<Product> products) {
        for (Product product: products) {
            System.out.println(product.toString());
        }
    }
}

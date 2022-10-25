package Product;

import java.time.LocalDate;

public class Product {
    private final String name;
    private final LocalDate producedDate;
    private final LocalDate consumeByDate;
    private final Double price;

    public Product(String name, String producedDate, String consumeByDate, String price) {
        this.name = name;
        this.producedDate = LocalDate.parse(producedDate);
        this.consumeByDate = LocalDate.parse(consumeByDate);
        this.price = Double.valueOf(price);
    }

    public Product(String name, LocalDate producedDate, LocalDate consumeByDate, Double price) {
        this.name = name;
        this.producedDate = producedDate;
        this.consumeByDate = consumeByDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getProducedDate() {
        return producedDate;
    }

    public LocalDate getConsumeByDate() {
        return consumeByDate;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-25s%-25s%-25s%-25s", name, producedDate.toString(), consumeByDate.toString(), price.toString());
    }
}

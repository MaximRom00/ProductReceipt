package by.romanchuk.entity;

import by.romanchuk.service.ProductService;
import by.romanchuk.io.ReaderWriter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CashBox {
    private final DiscountCart discountCart;
    private final Map<Product, Integer> products = new HashMap<>();
    private final double finalSum;
    private double discountSum;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final ProductService productService = ProductService.getInstance();

    /**
     * Constructor gets discountCart and map with id and count of product. Calculates final purchase sum.
     * Service is called to get all products by id and put it to Map<product, count>.
     *
     * @param discountCart
     * @param productsMap
     */
    public CashBox(DiscountCart discountCart, Map<Integer, Integer> productsMap) {
        this.discountCart = discountCart;
        for (Map.Entry<Integer, Integer> pr: productsMap.entrySet()){
            this.products.put(productService.getById(pr.getKey(), pr.getValue()), pr.getValue());
        }
        this.finalSum = calculateSum(products);
    }

    /**
     *
     * @param productsMap map(product, count of product) needs for calculate final sum.
     *                    If count of product more than 3 you will get sale 10%.
     *                    Also in this method we get discount sum.
     *
     * @return {@finalSum}
     */
    private double calculateSum(Map<Product, Integer> productsMap) {
        double sum = 0;

        for (Map.Entry<Product, Integer> product: productsMap.entrySet()){
            if (product.getValue() >= 5){
                sum += product.getKey().getPrice() * product.getValue() * 0.9;
                discountSum += product.getKey().getPrice() * product.getValue() * 0.1;
            }
            else{
                sum += product.getKey().getPrice() * product.getValue();
            }
        }

        return sum;
    }

    /**
     * Print formatting receipt and sends to writer. Receipt you may see in receipt.txt file.
     */
    public void printReceipt(){

        System.out.println(this);

        ReaderWriter.writer(this);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append(String.format("%25s\nDate: %tF\nTime: %s\nDiscountCard: %s, number: %s, discount: %d%%\n\n", "CASH RECEIPT.\n",
                LocalDate.now(), LocalTime.now().format(formatter),
                discountCart.getName(), discountCart.getCardNumber(), 10));

        string.append(String.format("%-17s %5s %10s %10s\n", "Item", "Qty", "Price", "Total"));
        string.append(String.format("%-17s %5s %10s %10s\n", "----", "---", "-----", "-----"));

        for (Map.Entry<Product, Integer> product: products.entrySet()){
            string.append(String.format("%-17s %5d %10.2f %10.2f\n",
            product.getKey().getName(), product.getValue(), product.getKey().getPrice(),product.getValue() >= 5 ? product.getValue() * product.getKey().getPrice() * 0.9 : product.getValue() * product.getKey().getPrice()));
        }

        string.append(String.format("\nDiscountSum: %.2f $\nFinalSum: %.2f $", discountSum, finalSum));

        return string.toString();
    }
}

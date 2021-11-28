package by.romanchuk;

import by.romanchuk.entity.CashBox;
import by.romanchuk.entity.DiscountCart;
import by.romanchuk.exception.ArgsException;
import by.romanchuk.io.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class Runner {
    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        CashBox cashBox = getCashBos(args);
        cashBox.printReceipt();
    }

    /**
     * Get args and parses it.
     *
     * Example for args: (3-4 5-2 1-1 card-123),
     * but you can write to com. line this address: src/main/resources/arguments.txt (file with args).
     *
     * @param  args   first number in args it's id product(There are 6 types of products),
     *                second it's product count(you may choose how many number of product you want,
     *                if number of product will more than product has you will get exception).
     *                You may see in dao package all products.
     *
     * @return {@link CashBox}
     */
    public static CashBox getCashBos(String[] args) {
        DiscountCart discountCart = null;
        Map<Integer, Integer> productsArgs = new HashMap<>();

        try {
            if (args[0].matches("\\D+[tx]")) {
                args = ReaderWriter.reader(new File(args[0]));
            }

            for (String arg : args) {

                if (arg.matches("[a-zA-Z]*-\\w*")) {
                    String name = arg.split("-")[0];
                    int cartNumber = Integer.parseInt(arg.split("-")[1]);

                    discountCart = new DiscountCart(name, cartNumber);
                }
                else if (arg.matches("\\w*-\\w*")) {
                    int id = Integer.parseInt(arg.split("-")[0]);
                    int count = Integer.parseInt(arg.split("-")[1]);

                    productsArgs.put(id, count);
                }
                else {
                    log.error(" Exception,\nYou args: {}", args);
                    throw new ArgsException("Incorrect args, try again. Example for args: (3-4 5-2 1-1 card-123)");
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            log.error(" Exception: {},\nYou args: {}", ex, args);
            throw new ArgsException("Incorrect args, try again. Example for args: (3-4 5-2 1-1 card-123)");
        }

        return new CashBox(discountCart, productsArgs);
    }
}

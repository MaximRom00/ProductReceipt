package by.romanchuk.dao;

import by.romanchuk.entity.Product;
import by.romanchuk.entity.products.*;
import by.romanchuk.exception.DeleteProduct;
import by.romanchuk.exception.NotEnoughProductCount;
import by.romanchuk.exception.NotFoundProduct;
import by.romanchuk.exception.ProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

public class ProductDao implements DaoInterface<Integer, Product>{
    private static final Logger log = LoggerFactory.getLogger(ProductDao.class);

    private static final ProductDao INSTANCE = new ProductDao();

    private final List<Product> products;
    private static int ID;

    public ProductDao() {
        this.products = new ArrayList<>();
                products.add(new Fridge(++ID,"Bosh Super Fridge", 500, 17));
                products.add(new GameConsole(++ID,"Xbox Series S", 400, 11));
                products.add(new Laptop(++ID,"Asus Zenbook 14", 1000, 25));
                products.add(new Microwave(++ID,"LG Hot Wave",250, 50));
                products.add(new MobilePhone(++ID,"Samsung S11", 999, 13));
                products.add(new TV(++ID,"Philips Q123654", 499, 10));
        log.info("Products successfully created: {} element", products.size());
    }


    public List<Product> getAllProducts(){
        log.info("All products successfully loaded. Products: {}", products);
        return products;
    }

    /**
     * Searching in list product with passed id, if we don't find it we get exception.
     * After that checking we compare count with product.count, if passed count more than product.count we get excepiton.
     * @param id
     * @param count
     * @return {@link Product} product from list.
     */
    public Product getById(Integer id, Integer count){
        Product product = null;

        try {
            product = products.stream()
                    .filter(prod -> prod.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new ProductException("Product not found"));
        }
        catch (ProductException ex) {
            log.error("Exception: {}.You id: {}", ex, id);
            throw new NotFoundProduct(ex);
        }
        try {
            Stream.of(product).filter(Objects::nonNull)
                    .filter(prod -> prod.getCount() > count)
                    .findFirst()
                    .orElseThrow(()-> new ProductException("There aren't so many products"));
        }
        catch (ProductException ex){
            log.error("Exception: {}.You count: {}", ex, count);
            throw new NotEnoughProductCount(ex);
        }

        log.info("Product with id: {} successfully loaded. Product: {}", id, product);
        return product;
    }

    /**
     * If id=0 we add new product to list, if there is a product with id in list we update it.
     * If there isn't id in list we get exception.
     * @param product
     */
    public void saveOrUpdate(Product product) {
        if (product.getId() == 0){
            int id = products.get(products.size() - 1).getId();
            product.setId(++id);
            products.add(product);
            log.info("Product with id: {} successfully added. Product: {}", id, product);
        }
        else {
            products.set(product.getId() - 1, product);
            log.info("Product with id: {} successfully changed. Product: {}", product.getId(), product);
        }
    }

    public void delete(Integer id) {
         if (id > products.size()){
            log.error("You can't delete product with id: {}", id);
            throw new DeleteProduct("You can't delete product with id: ", id);
        }
         else {
             products.remove(id - 1);
             log.info("Product with id: {} successfully deleted.", id);
         }
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}

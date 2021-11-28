package by.romanchuk.service;

import by.romanchuk.dao.ProductDao;
import by.romanchuk.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
/*
Spring используется только для web части.
Компонент слоя Service создается для внедрения в контроллеры.
Слой Dao не инжексится в слой Service.(т.к. )
Слоистость(mvc) продемонстрирована наглядно.
 */
@Service
public class ProductService implements ServiceInterface<Integer, Product>{
    private static final ProductService INSTANCE = new ProductService();
    private final ProductDao productDao;

    public ProductService() {
        this.productDao = ProductDao.getInstance();
    }

    public List<Product> getAllProducts(){
        return productDao.getAllProducts();
    }

    public Product getById(Integer id, Integer count){
        return productDao.getById(id, count);
    }

    public void saveOrUpdate(Product product) {
        productDao.saveOrUpdate(product);
    }

    public void delete(Integer id) {
        productDao.delete(id);
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }

}

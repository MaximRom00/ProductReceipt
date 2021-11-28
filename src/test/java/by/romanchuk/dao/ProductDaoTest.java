package by.romanchuk.dao;

import by.romanchuk.entity.Product;
import by.romanchuk.entity.products.Fridge;
import by.romanchuk.entity.products.MobilePhone;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDaoTest {
    private ProductDao productDao;

    @Before
    public void createProducts(){
        this.productDao = ProductDao.getInstance();
    }

    @Test
    public void shouldReturnProduct(){
        Product byId = productDao.getById(5, 5);
        assertThat(byId).isNotNull();
    }

    @Test
    public void shouldSaveAndUpdateProduct() {
        Fridge testProduct = new Fridge(0, "Samsung S11", 999, 13);

        List<Product> beforeSave = productDao.getAllProducts();

        productDao.saveOrUpdate(testProduct);

        List<Product> afterSave = productDao.getAllProducts();

        assertThat(beforeSave).isNotEqualTo(afterSave.size());

        afterSave.get(afterSave.size() - 1).setCount(999);
    }

    @Test
    public void shouldDeleteProduct(){
        int beforeDeleteSize = productDao.getAllProducts().size();

        Product product = productDao.getById(1,1);
        assertThat(product).isNotNull();

        productDao.delete(product.getId());

        List<Product> afterRemove = productDao.getAllProducts();

        assertThat(beforeDeleteSize).isNotEqualTo(afterRemove.size());

    }

    @Test
    public void shouldReturnAllProducts(){
        List<Product> products = productDao.getAllProducts();
            assertThat(products).contains(new MobilePhone(5,"Samsung S11", 999, 13));

    }
}

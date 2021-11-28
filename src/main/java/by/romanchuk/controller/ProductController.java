package by.romanchuk.controller;

import by.romanchuk.entity.Product;
import by.romanchuk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.stereotype.Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Main page which displays all products.
     *
     * @param model is being sent to main.jsp, model has all our products.
     *
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "main";
    }

    /**
     * We getting one product and sending it to changeProduct.jsp. There we see old information about our
     * product and we can change it. After that jsp page sending change model to 'save' for updating.
     *
     * @param id
     * @param model
     *
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam("id") int id, Model model){
        Product product = productService.getById(id, 0);
        System.out.println("tut");
        model.addAttribute("product", product);
        return "changeProduct";
    }

    /**
     * Here we getting product from changeProduct.jsp and product is sending to save or update.
     * This request is used for 'update' and 'save'.
     *
     * @param product
     *
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("product") Product product){
        productService.saveOrUpdate(product);
        return "redirect:/";
    }

    /**
     *
     * @param id we getting id and send it for delete. After that me return to main page.
     *
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id){
        productService.delete(id);
        return "redirect:/";
    }

    /**
     * Model is sending to changeProduct.jsp and after answer go to 'save' for saving new product.
     *
     * @param model
     *
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String change(Model model){
        model.addAttribute(new Product());
        return "changeProduct";
    }
}

package by.romanchuk.controller;

import by.romanchuk.entity.Product;
import by.romanchuk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ReceiptController {

    @Autowired
    private ProductService productService;

    /**
     * Example: http://localhost:8080/check?3=2&2=5&5=5;
     *
     * Here we may write some information in uri and getting new receipt.
     * But be attention you need write in uri only number, example: 1=2 & 3=3 because we getting map.
     * First number it's id product, second product count.
     *
     * @param parameters first - id, second - count, only number not like: id=1 & count=2
     *
     * @param model is sent to receipt.jsp for complete new receipt and printing it.
     * @return
     */
    @RequestMapping(value = "/check")
    public String getReceipt(@RequestParam Map<String, String> parameters, Model model){
        Map<Product, Integer> products = new HashMap<>();

        for (Map.Entry<String , String> param: parameters.entrySet()){
            products.put(productService.getById(Integer.parseInt(param.getKey()), Integer.parseInt(param.getValue())), Integer.parseInt(param.getValue()));
        }

        model.addAttribute("products", products);
        return "receipt";
    }

}

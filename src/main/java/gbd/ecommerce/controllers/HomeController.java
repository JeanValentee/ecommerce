package gbd.ecommerce.controllers;


import gbd.ecommerce.models.Product;
import gbd.ecommerce.models.User;
import gbd.ecommerce.services.product.LoadLastProducts;
import gbd.ecommerce.services.utils.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private LoadLastProducts loadLastProducts;


    @GetMapping("/home")
    public ModelAndView showHome() {
        List<Product> products;
        products = loadLastProducts.loadProducts();

        User user = LoggedUser.getUser();
        return new ModelAndView("/home/index.html").addObject("products", products).addObject("user", user);
    }
}

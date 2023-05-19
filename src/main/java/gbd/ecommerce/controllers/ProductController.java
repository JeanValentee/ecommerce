package gbd.ecommerce.controllers;

import gbd.ecommerce.models.Product;
import gbd.ecommerce.models.User;
import gbd.ecommerce.services.product.FindByCategory;
import gbd.ecommerce.services.product.FindCategoryById;
import gbd.ecommerce.services.product.LoadAllProducts;
import gbd.ecommerce.services.product.StoreProduct;
import gbd.ecommerce.services.utils.LoggedUser;
import gbd.ecommerce.services.utils.Message;
import gbd.ecommerce.validators.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private StoreProduct storeProduct;

    @Autowired
    private FindCategoryById findCategoryById;

    @Autowired
    private LoadAllProducts loadAllProducts;

    @Autowired
    private FindByCategory findByCategory;

    @InitBinder("product")
    public void initUserBinder(WebDataBinder binder) {
        binder.setValidator(new ProductValidator());
    }

    @GetMapping("/productRegister")
    public ModelAndView productRegister(Product product) {
        User user = LoggedUser.getUser();
        return new ModelAndView("/product/index").addObject("product",product).addObject("user", user);
    }

    @GetMapping("/product/{category}")
    public ModelAndView allProducts(@PathVariable("category") String category) {

        List<Product> products;
        products = findByCategory.execute(category);

        User user = LoggedUser.getUser();
        return new ModelAndView("/product/AllProducts").addObject("products", products).addObject("user", user);
    }

    @PostMapping("/productRegister")
    public ModelAndView store(@Valid Product product, BindingResult result, @RequestParam(value="file", required = false) MultipartFile file, RedirectAttributes redirect) throws IOException {

         
        product.setImage(file.getBytes());
        if(result.hasErrors()){
            return productRegister(product).addObject("message", Message.fieldsErrors());
        }

        try {

            product.setCategory(findCategoryById.execute(product.getCategory().getId_category()));
            storeProduct.execute(product);
            redirect.addFlashAttribute("message", Message.successMessage("Produto Cadastrado com Sucesso"));
            return new ModelAndView("home/index.html");

        } catch (RuntimeException error) {

            return productRegister(product).addObject("message", Message.errorMessage(error.getMessage()));

        } catch (Exception error) {

            error.printStackTrace();
            return productRegister(product).addObject("message", Message.internalError());
        }
    }
}

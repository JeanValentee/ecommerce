package gbd.ecommerce.controllers;

import gbd.ecommerce.models.Product;
import gbd.ecommerce.models.Purchase;
import gbd.ecommerce.models.User;
import gbd.ecommerce.repos.ProductRepository;
import gbd.ecommerce.repos.PurchaseRepository;
import gbd.ecommerce.services.purchase.LoadAllPurchases;
import gbd.ecommerce.services.purchase.StorePurchase;
import gbd.ecommerce.services.utils.LoggedUser;
import gbd.ecommerce.services.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorePurchase storePurchase;

    @Autowired
    private LoadAllPurchases loadAllPurchases;


    @GetMapping("/purchase/{idProduct}")
    public ModelAndView showPurchase(Purchase purchase, @PathVariable("idProduct") Integer idProduct){

        Product product;
        product = productRepository.findByIdProduct(idProduct);

        User user = LoggedUser.getUser();
        return new ModelAndView("/purchase/index.html").addObject("purchase", purchase).addObject("product", product).addObject("user", user);
    }

    @PostMapping("/purchase/{idProduct}")
    public ModelAndView store(@PathVariable("idProduct") int idProduct, @Valid Purchase purchase){

        Product product = productRepository.findByIdProduct(idProduct);
        purchase.setProduct(product);
        User user = LoggedUser.getUser();
        purchase.setUser(user);

        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        purchase.setDate(out);

        purchase.setTotal(purchase.getProduct().getPrice() * purchase.getQuantity());

        storePurchase.execute(purchase);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/myPurchase")
    public ModelAndView showMyPurchase(){
        User user = LoggedUser.getUser();
        Purchase purchase;
        List<Purchase> purchases1;
        purchases1 = loadAllPurchases.loadPurchase();
        List<Purchase> purchases2 = new ArrayList<Purchase>();
        for(int i = 0; i < purchases1.size(); i++){
            if(purchases1.get(i).getUser().getId_user() == user.getId_user()){
                purchases2.add(purchases1.get(i));
            }
        }

        return new ModelAndView("/purchase/myPurchase.html").addObject("purchases2", purchases2).addObject("user", user);
    }

}

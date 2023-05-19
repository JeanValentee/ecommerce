package gbd.ecommerce.controllers;


import gbd.ecommerce.models.Product;
import gbd.ecommerce.models.User;
import gbd.ecommerce.services.user.StoreUser;
import gbd.ecommerce.services.user.UserAuthenticated;
import gbd.ecommerce.services.utils.Message;
import gbd.ecommerce.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;


@Controller
public class LoginController {

    @Autowired
    private StoreUser storeUser;

    @Autowired
    private UserAuthenticated userAuthenticated;

    @InitBinder("user")
    public void initUserBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("login/index");
    }

    @GetMapping("/signup")
    public ModelAndView signup(User user) {
        return new ModelAndView("signup/index").addObject("user",user);
    }


    @PostMapping("/signup")
    public ModelAndView store(@Valid User user, BindingResult result) {
        if(result.hasErrors()){
            return signup(user).addObject("message", Message.fieldsErrors());
        }

        storeUser.execute(user);

        return new ModelAndView("login/index.html");
    }

}

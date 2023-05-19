package gbd.ecommerce.services.utils;

import gbd.ecommerce.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedUser {

    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

package gbd.ecommerce.services.user;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticated {

    public boolean execute() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }

        return authentication.isAuthenticated();

    }
}

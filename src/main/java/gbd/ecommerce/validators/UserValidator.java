package gbd.ecommerce.validators;


import gbd.ecommerce.models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "field.required");

        User user = (User) object;

        if(!errors.hasFieldErrors("password") && user.getPassword().length() < 6){
            errors.rejectValue("password", "field.size");
        }
    }
}

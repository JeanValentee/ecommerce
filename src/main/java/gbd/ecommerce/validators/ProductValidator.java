package gbd.ecommerce.validators;

import gbd.ecommerce.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category.id_category", "field.required");


    }
}

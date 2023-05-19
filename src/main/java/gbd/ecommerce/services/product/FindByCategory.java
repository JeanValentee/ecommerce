package gbd.ecommerce.services.product;

import gbd.ecommerce.models.Category;
import gbd.ecommerce.models.Product;
import gbd.ecommerce.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindByCategory {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FindCategoryByName findCategoryByName;

    public List<Product> execute(String name){

        Category category = findCategoryByName.execute(name);
        List<Product> products = productRepository.findByCategory(category);
        return products;
    }

}

package gbd.ecommerce.services.product;

import gbd.ecommerce.models.Category;
import gbd.ecommerce.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindCategoryById {

    @Autowired
    private CategoryRepository repository;

    public Category execute(Integer id_category) {
        return repository.findById(id_category);
    }
}

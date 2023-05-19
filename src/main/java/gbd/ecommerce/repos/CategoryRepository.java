package gbd.ecommerce.repos;

import gbd.ecommerce.models.Category;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface CategoryRepository extends Repository<Category, Integer> {

    Category findById(Integer id_category);

    Category findByName(String name);
}

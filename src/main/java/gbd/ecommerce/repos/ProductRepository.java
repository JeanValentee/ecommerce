package gbd.ecommerce.repos;

import gbd.ecommerce.models.Category;
import gbd.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findAll();
    public List<Product> findTop3ByOrderByIdProductDesc();
    public List<Product> findByCategory(Category category);
    public Product findByIdProduct(Integer idProduct);

}

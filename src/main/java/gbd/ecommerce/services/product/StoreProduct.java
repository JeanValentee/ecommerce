package gbd.ecommerce.services.product;

import gbd.ecommerce.models.Product;
import gbd.ecommerce.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreProduct {

    @Autowired
    private ProductRepository repository;


    public void execute(Product product) {

        repository.save(product);
    }
}

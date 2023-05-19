package gbd.ecommerce.services.product;

import gbd.ecommerce.models.Product;
import gbd.ecommerce.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadAllProducts {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> loadProducts(){
        return (List<Product>) productRepository.findAll();
    }
}

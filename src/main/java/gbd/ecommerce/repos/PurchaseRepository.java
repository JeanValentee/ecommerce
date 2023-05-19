package gbd.ecommerce.repos;


import gbd.ecommerce.models.Product;
import gbd.ecommerce.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    public List<Purchase> findAll();
}

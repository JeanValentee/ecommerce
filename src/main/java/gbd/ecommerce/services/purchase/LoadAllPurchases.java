package gbd.ecommerce.services.purchase;

import gbd.ecommerce.models.Purchase;
import gbd.ecommerce.repos.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadAllPurchases {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> loadPurchase(){
        return (List<Purchase>) purchaseRepository.findAll();
    }
}

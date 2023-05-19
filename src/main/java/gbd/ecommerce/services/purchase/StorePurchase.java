package gbd.ecommerce.services.purchase;

import gbd.ecommerce.models.Purchase;
import gbd.ecommerce.repos.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorePurchase {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public void execute(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}

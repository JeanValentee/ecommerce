package gbd.ecommerce.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase")
    private int idPurchase;

    @Column(name="date",nullable = false)
    private Date date;

    @Column(name="quantity",nullable = false)
    private int quantity;

    @Column(name="total",nullable = false)
    private float total;

    @ManyToOne
    @JoinColumn(name = "id_product",nullable=false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_user",nullable=false)
    private User user;


}

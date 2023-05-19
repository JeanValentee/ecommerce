package gbd.ecommerce.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int idProduct;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="price",nullable = false)
    private float price;

    @Column(name="quantity",nullable = false)
    private int quantity;


    private byte[] image;

    @Column(name="size",nullable = false)
    private String size;

    @Column(name="color",nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private Category category;

}

package gbd.ecommerce.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {

    @Id
    private int id_category;

    @Column(name="name",nullable = false)
    private String name;


}

package gbd.ecommerce.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_role")
    private int id_role;

    @Column(name="name")
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}

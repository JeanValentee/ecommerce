package gbd.ecommerce.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name="user", schema="public")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private int id_user;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="email",nullable = false, unique = true)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="address",nullable = false)
    private String address;

    @Column(name="cpf",nullable = false)
    private String cpf;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        String[] names = this.name.split(" ");

        String shortName = names[0];

        if (names.length > 1)
            shortName += " " + names[names.length - 1];

        return shortName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

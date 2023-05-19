package gbd.ecommerce.repos;

import gbd.ecommerce.models.Role;
import org.springframework.data.repository.Repository;


public interface RoleRepository extends Repository<Role, Integer> {

    Role findByName(String name);
}

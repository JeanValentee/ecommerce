package gbd.ecommerce.services.role;

import gbd.ecommerce.models.Role;
import gbd.ecommerce.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindRoleByName {

    @Autowired
    private RoleRepository repository;

    public Role execute(String name) {
        return repository.findByName(name);
    }
}

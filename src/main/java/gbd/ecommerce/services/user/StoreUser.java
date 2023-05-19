package gbd.ecommerce.services.user;

import gbd.ecommerce.models.Role;
import gbd.ecommerce.models.User;
import gbd.ecommerce.repos.UserRepository;
import gbd.ecommerce.services.role.FindRoleByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StoreUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    private FindRoleByName findRoleByName;

    @Autowired
    private PasswordEncoder encoder;

    public void execute(User user) {
        User userAlreadyExists = repository.findByEmail(user.getEmail());

        if(userAlreadyExists != null)
            System.out.println("Usuário já está cadastrado no sistema.");

        else {

            user.setPassword(encoder.encode(user.getPassword()));

            Role role = findRoleByName.execute("ROLE_USER");

            user.getRoles().add(role);

            repository.save(user);
        }
    }
}

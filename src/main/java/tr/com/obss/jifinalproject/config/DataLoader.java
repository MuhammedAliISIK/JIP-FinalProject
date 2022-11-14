package tr.com.obss.jifinalproject.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tr.com.obss.jifinalproject.entity.Role;
import tr.com.obss.jifinalproject.repo.RoleRepository;

@Component
public class DataLoader implements ApplicationRunner {
    private final RoleRepository roleRepository;
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public DataLoader(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var userRoleExists = roleRepository.existsByName(ROLE_USER);
        if(!userRoleExists){
            var userRole = new Role();
            userRole.setName(ROLE_USER);
            roleRepository.save(userRole);
        }

        var adminRoleExists = roleRepository.existsByName(ROLE_ADMIN);
        if(!adminRoleExists){
            var adminRole = new Role();
            adminRole.setName(ROLE_ADMIN);
            roleRepository.save(adminRole);
        }
    }
}

package hillel.jee.security.services;

import hillel.jee.security.domain.Role;
import hillel.jee.security.domain.Role.RoleName;
import hillel.jee.security.domain.User;
import hillel.jee.security.repositories.RoleRepository;
import hillel.jee.security.repositories.UserRepository;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository,
      RoleRepository roleRepository,
      BCryptPasswordEncoder passwordEncoder) {

    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean userExists(String username) {
    return userRepository.findByUsername(username).isPresent();
  }

  public void createUser(User user, Role.RoleName ...roles) {
    Set<String> roleNames = Arrays
        .stream(roles)
        .map(RoleName::asRoleNameString)
        .collect(Collectors.toSet());

    if (roleNames.isEmpty()) {
      roleNames.add(RoleName.USER.asRoleNameString());
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(roleRepository.findAllByNameIn(roleNames));

    userRepository.save(user);
  }

  public void createRole(RoleName roleName) {
    roleRepository.save(
        Role.builder()
            .name(roleName.asRoleNameString())
            .build()
    );
  }

  public UserDetails get(String username) {
    return null;
  }
}

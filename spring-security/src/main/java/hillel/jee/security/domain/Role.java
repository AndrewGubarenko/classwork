package hillel.jee.security.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  @Transient
  RoleName roleName;

  public enum RoleName {
    ADMIN("ADMIN"),
    MODERATOR("MODERATOR"),
    USER("USER");

    private final String roleName;

    RoleName(String roleName) {
      this.roleName = roleName;
    }

    public String shorten() {
      return roleName;
    }

    public String asRoleNameString() {
      return "ROLE_" + roleName;
    }
  }
}

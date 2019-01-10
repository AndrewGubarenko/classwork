package hillel.jee.persistence.students;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
  @NonNull
  @Id
  private Integer id;
  @NonNull
  @Column
  private String name;
}

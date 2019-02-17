package hillel.jee.spring.persistence.jpa.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students_scores")
@EqualsAndHashCode
public class StudentScore {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String subject;

  @Column
  private Integer score;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @Override
  public String toString() {
    return "StudentScore{" +
        "subject='" + subject + '\'' +
        ", score=" + score +
        '}';
  }
}

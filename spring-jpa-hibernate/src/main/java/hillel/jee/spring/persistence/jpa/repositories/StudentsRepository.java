package hillel.jee.spring.persistence.jpa.repositories;

import hillel.jee.spring.persistence.jpa.entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentsRepository {
  @PersistenceContext
  EntityManager entityManager;

  public List<Student> findAll() {
    return entityManager
        .createQuery("SELECT s FROM Student s "
            + "INNER JOIN FETCH s.studentInfo "
            + "INNER JOIN FETCH s.studentScores ", Student.class)
        .getResultList();
  }

  public Student findOne(Integer studentId) {
    return entityManager
        .createQuery(""
            + "SELECT s FROM Student s "
            + "JOIN FETCH s.studentInfo "
            + "JOIN FETCH s.studentScores "
            + "WHERE s.id = :studentId", Student.class)
        .setParameter("studentId", studentId)
        .getSingleResult();
  }

  public Student save(Student student) {
    entityManager.persist(student);
    return student;
  }
}

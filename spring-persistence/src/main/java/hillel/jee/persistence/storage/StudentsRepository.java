package hillel.jee.persistence.storage;

import hillel.jee.persistence.students.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//public interface StudentsRepository extends JpaRepository<Student, Integer> {
public interface StudentsRepository extends CrudRepository<Student, Integer> {
  List<Student> findAll();
}

package hillel.jee.persistence.storage;

import hillel.jee.persistence.students.Student;
import java.util.List;

public interface StudentsDao {
  List<Student> allStudents();

  Student read(int id);

  int create(Student student);

  void update(Student student);

  void delete(int id);
}

package hillel.jee.persistence.storage;

import hillel.jee.persistence.students.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsInMemoryDao implements StudentsDao {
  private Map<Integer, Student> studentsMap = new HashMap<>();

  public StudentsInMemoryDao(List<Student> students) {
    students.forEach(this::create);
  }

  public List<Student> allStudents() {
    return new ArrayList<>(studentsMap.values());
  }

  @Override
  public Student read(int id) {
    return studentsMap.get(id);
  }

  @Override
  public int create(Student student) {
    studentsMap.put(student.getId(), student);
    return student.getId();
  }

  @Override
  public void update(Student student) {
    studentsMap.put(student.getId(), student);
  }

  @Override
  public void delete(int id) {
    studentsMap.remove(id);
  }
}

package hillel.jee.persistence.storage;

import hillel.jee.persistence.students.Student;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentsJdbcDao implements StudentsDao {

  private final JdbcTemplate jdbcTemplate;

  public StudentsJdbcDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Student> allStudents() {
    return jdbcTemplate.query(
        "SELECT * FROM students",
        new BeanPropertyRowMapper<>(Student.class)
    );
  }

  @Override
  public Student read(int id) {
    return jdbcTemplate.queryForObject(
        "SELECT * FROM students WHERE id = ?",
        new Object[] { id },
        new BeanPropertyRowMapper<>(Student.class)
    );
  }

  @Override
  public int create(Student student) {
    int updated = jdbcTemplate.update(
        "INSERT INTO students VALUES(?, ?)",
        student.getId(),
        student.getName()
    );
    return updated > 0 ? student.getId() : -1;
  }

  @Override
  public void update(Student student) {
    jdbcTemplate.update(
        "UPDATE students SET name = ? WHERE id = ?",
        student.getName(),
        student.getId()
      );
  }

  @Override
  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
  }
}

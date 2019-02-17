package hillel.jee.persistence.students;

import hillel.jee.persistence.storage.StudentsDao;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsController {

  private final StudentsDao studentsDao;

  public StudentsController(
//      @Qualifier("studentsInMemoryDao") StudentsDao studentsDao) {
//      @Qualifier("studentsJdbcDao") StudentsDao studentsDao) {
      @Qualifier("studentsHibernateDao") StudentsDao studentsDao) {
    this.studentsDao = studentsDao;
  }

  @GetMapping("/students")
  public List<Student> students() {
    return studentsDao.allStudents();
  }

  @GetMapping("/students/{id}")
  public Student students(@PathVariable Integer id) {
    return studentsDao.read(id);
  }

  @PostMapping("/students")
  public ResponseEntity<Student> create(@RequestBody Student student) {
    studentsDao.create(student);
    return ResponseEntity.status(HttpStatus.CREATED).body(student);
  }

  @PutMapping("/students")
  public ResponseEntity<Void> update(@RequestBody Student student) {
    studentsDao.update(student);
    return ResponseEntity
        .noContent()
        .build();
  }

  @DeleteMapping("/students/{id}")
  public ResponseEntity<Void> delete(@PathVariable int id) {
    studentsDao.delete(id);
    return ResponseEntity
        .noContent()
        .build();
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResult> error(EmptyResultDataAccessException e) {
    return ResponseEntity
        .badRequest()
        .body(new ErrorResult(e.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResult> error(Exception e) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResult(e.getMessage()));
  }

  @Data
  private static class ErrorResult {
    @NonNull
    private String message;
  }
}

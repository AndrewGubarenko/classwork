package hillel.jee.spring.persistence.jpa.services;

import hillel.jee.spring.persistence.jpa.entities.Student;
import hillel.jee.spring.persistence.jpa.repositories.StudentsRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentsService {

  private final StudentsRepository studentsRepository;

  public StudentsService(StudentsRepository studentsRepository) {
    this.studentsRepository = studentsRepository;
  }

  public List<Student> findAll() {
    return studentsRepository.findAll();
  }

  public Student findOne(Integer studentId) {
    return studentsRepository.findOne(studentId);
  }

  @Transactional
  public Student save(Student student) {
    return studentsRepository.save(student);
  }
}

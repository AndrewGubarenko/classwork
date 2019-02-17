package hillel.jee.persistence.students;

import hillel.jee.persistence.storage.StudentsDao;
import hillel.jee.persistence.storage.StudentsRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentsService {

  private final StudentsDao studentsDao;
  private final StudentsRepository studentsRepository;

  public StudentsService(
//      @Qualifier("studentsInMemoryDao") StudentsDao studentsDao,
//      @Qualifier("studentsJdbcDao") StudentsDao studentsDao,
    StudentsRepository studentsRepository) {
    this.studentsRepository = studentsRepository;
    this.studentsDao = null;//studentsDao;
  }

  private boolean isDaoConfigured() {
    return studentsDao != null;
  }

  List<Student> allStudents() {
    if (isDaoConfigured()) {
      return studentsDao.allStudents();
    }
    return studentsRepository.findAll();
  }

  Student read(int id) {
    if (isDaoConfigured()) {
      return studentsDao.read(id);
    }
    return studentsRepository.findById(id).orElseThrow();
  }

  @Transactional
  int create(Student student) {
    if (isDaoConfigured()) {
      return studentsDao.create(student);
    }
    return studentsRepository.save(student).getId();
  }

  void update(Student student) {
    if (isDaoConfigured()) {
      studentsDao.update(student);
    }
    studentsRepository.save(student);
  }

  void delete(int id) {
    if (isDaoConfigured()) {
      studentsDao.delete(id);
    }
    studentsRepository.deleteById(id);
  }
}

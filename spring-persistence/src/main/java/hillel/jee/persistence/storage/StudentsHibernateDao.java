package hillel.jee.persistence.storage;

import hillel.jee.persistence.students.Student;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Transactional
public class StudentsHibernateDao implements StudentsDao {

  private final SessionFactory sessionFactory;

  public StudentsHibernateDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public List<Student> allStudents() {
    return getSession()
        .createQuery("from Student")
        .list();
  }

  @Override
  public Student read(int id) {
    return getSession().get(Student.class, id);
  }

  @Override
  public int create(Student student) {
    getSession().persist(student);
    return student.getId();
  }

  @Override
  public void update(Student student) {
    getSession().merge(student);
  }

  @Override
  public void delete(int id) {
    Student student = read(id);
    getSession().delete(student);
  }
}

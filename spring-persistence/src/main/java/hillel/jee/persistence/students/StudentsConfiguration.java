package hillel.jee.persistence.students;

import hillel.jee.persistence.storage.StudentsDao;
import hillel.jee.persistence.storage.StudentsHibernateDao;
import hillel.jee.persistence.storage.StudentsInMemoryDao;
import hillel.jee.persistence.storage.StudentsJdbcDao;
import java.util.List;
import java.util.function.Function;
import javax.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@Configuration
public class StudentsConfiguration {
  @Bean
  public StudentsDao studentsInMemoryDao() {
    return new StudentsInMemoryDao(predefinedStudentsList());
  }

  @Bean
  public StudentsDao studentsJdbcDao(JdbcTemplate jdbcTemplate) {
    return new StudentsJdbcDao(jdbcTemplate);
  }

  @Bean
  public StudentsDao studentsHibernateDao(EntityManagerFactory entityManagerFactory) {
    return new StudentsHibernateDao(entityManagerFactory.unwrap(SessionFactory.class));
  }

  @Bean
  public HibernateTransactionManager hibernateTransactionManager(EntityManagerFactory entityManagerFactory) {
    return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
  }

  @Bean
  public CommandLineRunner studentsDbData(JdbcTemplate jdbcTemplate, StudentsDao studentsJdbcDao) {
    return args -> {
      jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS students ("
          + "    id int primary key,"
          + "    name varchar(255)"
          + ")");
      predefinedStudentsList().forEach(studentsJdbcDao::create);
    };
  }

  @Bean
  public CommandLineRunner studentsDbDataHibernate(StudentsDao studentsHibernateDao) {
    return args -> predefinedStudentsList().forEach(studentsHibernateDao::create);
  }
  @Bean
  public List<Student> predefinedStudentsList() {
    return List.of(
        new Student(1, "John Doe"),
        new Student(2, "Jane Roe"),
        new Student(3, "James Bond"),
        new Student(4, "Michael Jackson")
    );
  }
}

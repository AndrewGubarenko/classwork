package hillel.jee.spring.persistence.jpa;

import hillel.jee.spring.persistence.jpa.entities.Student;
import hillel.jee.spring.persistence.jpa.entities.StudentInfo;
import hillel.jee.spring.persistence.jpa.services.StudentsService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaHibernateApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SpringJpaHibernateApplication.class, args);
  }

  @Autowired
  private StudentsService studentsService;

  @Override
  public void run(String... args) throws Exception {
    studentsService.findAll().forEach(System.out::println);
    System.out.println("------------");
    System.out.println(studentsService.findOne(1).toString());

    Student st = Student.builder()
        .id(4)
        .name("Drako Malfoy")
        .email("drako.malfoy@hogwarts.edu")
        .studentScores(Collections.emptyList())
        .build();

//    StudentInfo info = new StudentInfo();
//    info.setStudent(st);
//
//    st.setStudentInfo(info);
//
//    studentsService.save(st);
//
//    System.out.println("------------");
//    System.out.println(studentsService.findOne(4).toString());
  }
}


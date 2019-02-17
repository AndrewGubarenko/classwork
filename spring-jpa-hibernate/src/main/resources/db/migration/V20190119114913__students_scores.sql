create table students_scores (
      id int primary key auto_increment,
      subject varchar(255) not null,
      score int not null,
      student_id int not null,
      foreign key (student_id) references students(id)
);

insert into students_scores(subject, score, student_id) values
      ('math', 10, 1),
      ('math', 5, 2),
      ('math', 11, 3),
      ('physics', 10, 1),
      ('physics', 8, 2),
      ('physics', 10, 3),
      ('magic#2', 10, 1),
      ('magic#1', 9, 2),
      ('magic#1', 12, 3)


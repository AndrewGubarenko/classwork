create table students_info (
  id int primary key AUTO_INCREMENT,
  keys text,
  student_id int not null,
  foreign key (student_id) references students(id)
);

insert into students_info(student_id, keys)
  values
      (1, '0xDEADBEAF'),
      (2, '0xCAFEBABE');

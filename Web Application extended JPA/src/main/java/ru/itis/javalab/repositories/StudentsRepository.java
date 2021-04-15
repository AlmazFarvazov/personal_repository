package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.javalab.models.Student;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByFirstNameIsLikeIgnoreCaseAndAgeAfter(String firstName, int age);

    @Query("select student from Student student where student.age >= :age " +
            "and (upper(student.firstName) like concat('%', upper(:name), '%') " +
            "       or upper(student.lastName) like  concat('%', upper(:name), '%'))")
    List<Student> search(@Param("name")String name, @Param("age") Integer age);
}

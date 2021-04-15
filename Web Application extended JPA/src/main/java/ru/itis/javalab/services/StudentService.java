package ru.itis.javalab.services;

import ru.itis.javalab.dto.StudentDto;
import ru.itis.javalab.models.Student;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    List<StudentDto> getAllStudents(int page, int size);
    void addStudent(StudentDto studentDto);

    StudentDto getStudent(Long studentId);
}

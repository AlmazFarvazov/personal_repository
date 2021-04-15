package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.StudentDto;
import ru.itis.javalab.models.Student;
import ru.itis.javalab.repositories.StudentsRepository;
import ru.itis.javalab.utils.EmailUtil;
import ru.itis.javalab.utils.MailsGenerator;

import java.util.List;
import java.util.UUID;

import static ru.itis.javalab.dto.StudentDto.from;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    MailsGenerator mailsGenerator;

    @Value("${server.url}")
    private String serverUrl;

    @Autowired
    EmailUtil emailUtil;

    @Value("${spring.mail.username}")
    private String from;

    private StudentsRepository studentsRepository;

    public StudentServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return StudentDto.from(studentsRepository.findAll());
    }

//    @Override
//    public List<Student> getAllStudentsByAge(int age) {
//        return studentsRepository.findAllByAge(age);
//    }

    @Override
    public List<StudentDto> getAllStudents(int page, int size) {
//        return from(studentsRepository.findAll(page, size));
        return null;
    }

    @Override
    public void addStudent(StudentDto studentDto) {
        Student student = Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .confirmCode(UUID.randomUUID().toString())
                .build();
        studentsRepository.save(student);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, student.getConfirmCode());
//        emailUtil.sendMail("almaz.farvazov2001@gmail.com", "Тест", from, confirmMail);
    }

    @Override
    public StudentDto getStudent(Long studentId) {
        return StudentDto.from(studentsRepository.findById(studentId).orElse(null));
    }

}

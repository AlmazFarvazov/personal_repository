package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.StudentDto;
import ru.itis.javalab.services.StudentService;

import java.util.List;

@Controller
public class StudentsController {

    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudentsPage(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "size", required = false) Integer size,
                                  Model model) {
        if (page != null && size != null) {
            model.addAttribute("students", studentService.getAllStudents(page, size));
        } else {
            model.addAttribute("students", studentService.getAllStudents());
        }
        return "students_page";
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public String addStudent(StudentDto student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @RequestMapping(value = "/students/{student-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StudentDto getStudent(@PathVariable("student-id") Long studentId) {
        return studentService.getStudent(studentId);
    }

    @RequestMapping(value = "/students/json", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StudentDto> addStudentFromJson(@RequestBody StudentDto student) {
        studentService.addStudent(student);
        return studentService.getAllStudents();
    }

}

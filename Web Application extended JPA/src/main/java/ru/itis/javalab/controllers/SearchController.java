package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.StudentDto;
import ru.itis.javalab.models.Student;
import ru.itis.javalab.repositories.StudentsRepository;

import java.util.List;

import static ru.itis.javalab.dto.StudentDto.from;

@Transactional
@RestController
public class SearchController {

    @Autowired
    private StudentsRepository studentsRepository;

    @RequestMapping(value = "search/students/byNameLikeAndAgeAfter", method = RequestMethod.GET)
    public List<StudentDto> searchByNameLikeAndAgeAfter(
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        return from(studentsRepository.search(name, age));
    }



}

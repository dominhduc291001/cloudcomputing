package com.cloudcomputing.backend.api;

import com.cloudcomputing.backend.model.StudentDTO;
import com.cloudcomputing.backend.service.StudentService;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/student/")
public class StudentAPI {
    @Autowired
    private StudentService studentService;

    @PostMapping("add")
    public StudentDTO addStudent(@RequestPart("student")  StudentDTO student,@RequestPart("file") @Nullable MultipartFile file) {
        StudentDTO st= studentService.addStudent(student,file);
        return  st;
    }

    @GetMapping("getAll")
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> lstStudent = studentService.getAllStudent();
        return lstStudent.size() > 0 ? lstStudent : null;
    }

    @GetMapping("getById/{mssv}")
    public StudentDTO getStudentById(@PathVariable Integer mssv) {
        StudentDTO st= studentService.getStudentById(mssv);
        return  st;
    }
    @PutMapping("update")
    public StudentDTO updateStudent(@RequestBody StudentDTO student) {
        StudentDTO newStudent = studentService.updateStudent(student);
        return newStudent;
    }
    @DeleteMapping("delete/{mssv}")
    public int deleteStudent(@PathVariable Integer mssv) {
        return studentService.deleteStudent(mssv);
    }
}

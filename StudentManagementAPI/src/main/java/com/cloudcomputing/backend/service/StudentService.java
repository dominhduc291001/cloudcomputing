package com.cloudcomputing.backend.service;

import com.cloudcomputing.backend.model.StudentDTO;
import com.cloudcomputing.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private AmazonClient amazonClient;
    @Autowired
    StudentRepository studentRepository;

    public StudentDTO addStudent(StudentDTO student, MultipartFile file) {
        try {
            if (file != null && !file.getName().isEmpty()) {
               String linkImg= amazonClient.uploadFile(file);
               student.setAvatar(linkImg);
            }
            StudentDTO st= studentRepository.save(student);
            return  st;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> lstStudent = studentRepository.findAll();
        return lstStudent.size() > 0 ? lstStudent : null;
    }
    public StudentDTO getStudentById(Integer mssv) {
        StudentDTO st= studentRepository.getByMssv(mssv);
        return  st;
    }
    public StudentDTO updateStudent(StudentDTO student) {
        StudentDTO oldStudent = studentRepository.getById(student.getMssv());
        if (!student.getFullname().isEmpty() && student.getFullname() != oldStudent.getFullname()) {
            oldStudent.setFullname(student.getFullname());
        }
        if (!student.getClassname().isEmpty() && student.getClassname() != oldStudent.getClassname()) {
            oldStudent.setClassname(student.getClassname());
        }
        if (!student.getSex().isEmpty() && student.getSex() != oldStudent.getSex()) {
            oldStudent.setSex(student.getSex());
        }
        if (student.getGpa() != oldStudent.getGpa()) {
            oldStudent.setGpa(student.getGpa());
        }
        StudentDTO newStudent = studentRepository.save(oldStudent);
        return newStudent;
    }
    public int deleteStudent(int mssv) {
        try {
            StudentDTO studentDTO = getStudentById(mssv);
            studentRepository.delete(studentDTO);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}

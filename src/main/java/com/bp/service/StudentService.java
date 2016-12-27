package com.bp.service;

import com.bp.entity.Student;

import java.util.List;

/**
 * Created by issuser on 2016/12/26.
 */
public interface StudentService {

    boolean createStudent(Student student);
    boolean updateStudent(Student student);
    Student getStudentById(int id);
    List<Student> getStudents(String name);

}

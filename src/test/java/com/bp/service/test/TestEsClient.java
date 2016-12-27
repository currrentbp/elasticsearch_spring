package com.bp.service.test;

import com.alibaba.fastjson.JSON;
import com.bp.entity.Student;
import com.bp.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by issuser on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:applicationContext.xml",
        "classpath*:elasticsearch.xml"})
public class TestEsClient  {

    @Autowired
    private StudentService studentService;


    @Test
    public void create(){
        Student student = new Student();
        student.setAge(10);
        student.setId(12);
        student.setAddress("asddddddd");
        student.setName("baopan");
        studentService.createStudent(student);
    }

    @Test
    public void update(){
        Student student = new Student();
        student.setAge(20);
        student.setId(12);
        student.setAddress("asddddddd==============");
        student.setName("baopan222222222");
        studentService.updateStudent(student);
    }

    @Test
    public void getOne(){
        Student student = studentService.getStudentById(12);
        System.out.println("===>student:"+ JSON.toJSONString(student));
    }

    @Test
    public void getList(){
        List<Student> studens = studentService.getStudents("baopan2");
        System.out.println("====>getList: students:"+JSON.toJSONString(studens));

    }
}

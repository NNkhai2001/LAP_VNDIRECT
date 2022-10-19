package edu.java.spring.dao;

import edu.java.spring.model.Student;

import java.util.List;

public interface StudentDAO {
    public void insert(Student student);
    public List<Student> list();
    public List<Student> listStudentByName(String name);
    public void Delete(int id);
    public Student getStudentById(int id);
    public void Update(Student student);
}

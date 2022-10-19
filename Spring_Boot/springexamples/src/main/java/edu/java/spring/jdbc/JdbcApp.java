package edu.java.spring.jdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JdbcApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        StudentJdbcDAO jdbc = (StudentJdbcDAO) context.getBean("studentJdbcDAO");
       // System.out.println("create bean "+jdbc);
        jdbc.insert("Tran Thi G",16);
        //jdbc.updateAgeByName("Tran Van A",15);
        //jdbc.DeleteStudent(201);
        //Add
//        List<Student> students = new ArrayList<>();
//        students.add(new Student("Tran thi C",17));
//        students.add(new Student("Tran Ngoc Z",20));
//        students.add(new Student("Tran thi X",22));
//        int[] values = jdbc.add(students);
//        for (int i = 0; i < values.length; i++) {
//            System.out.println("add record:"+i+":"+(values[i]==0?"failed":"success"));
//        }
//        jdbc.save("Tran Thi A","15");
        System.out.println("Total students is: " + jdbc.totalRecord());
        jdbc.loadStuddent("T").forEach(student -> System.out.println(student));
    }


}

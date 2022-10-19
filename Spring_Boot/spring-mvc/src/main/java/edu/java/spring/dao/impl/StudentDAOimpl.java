package edu.java.spring.dao.impl;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StudentDAOimpl implements StudentDAO, DisposableBean {
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
        //Create Table
    @PostConstruct
    private void createTableFNotExist() throws SQLException {
        DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
        if (rs.next()) {
            System.out.println("Table: " + rs.getString("TABLE_NAME") + "already exist!");
            return;
        }
        jdbcTemplate.execute("CREATE TABLE STUDENT(id BIGINT PRIMARY KEY GENERATED " +
                "ALWAYS  AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                "name VARCHAR(1000), age INTEGER)");
    }
        //Insert table
    public void insert(Student student) {
        jdbcTemplate.update("INSERT INTO STUDENT (name,age) VALUES (?,?)", student.getName(), student.getAge());
        System.out.println("Create Record Name= " + student.getName());
    }
    //Connect DB
    @Override
    public void destroy() throws Exception {
        DriverManager.getConnection("jdbc:derby:C/Maven/sampledb2;shutdown=true");
    }

    //Select all table
    @Override
    public List<Student> list() {
     List list =  jdbcTemplate.query("SELECT * FROM STUDENT",new StudentRowMapper());
     return list;
    }
    //Search
    @Override
    public List<Student> listStudentByName(String name) {
        List list =  jdbcTemplate.query("SELECT * FROM STUDENT WHERE NAME LIKE '%"+name+"%'",new StudentRowMapper());
        return list;
    }


    private final static class StudentRowMapper implements RowMapper<Student>{

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            return student;
        }
    }
    //Delete
    @Override
    public void Delete(int id) {
        jdbcTemplate.execute("delete from student where id="+id);
    }
    //getStudentById
    @Override
    public Student getStudentById(int id) {
        Student student = jdbcTemplate.queryForObject("select * from student where id="+id,new StudentRowMapper());
        return student;
    }


    //Update Student
    @Override
    public void Update(Student student) {
    jdbcTemplate.update("update student set name= ?, age = ? where id = ?",student.getName(),student.getAge(),student.getId());
    }


}

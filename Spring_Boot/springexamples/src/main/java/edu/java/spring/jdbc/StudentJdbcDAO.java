package edu.java.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StudentJdbcDAO {
    private static Logger LOGGER;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private String insertQuery;
    private String updateAgeByNameSQL = "update student set age=? where name=?";
    private String DelQuery;

    public void setDelQuery(String delQuery) {
        DelQuery = delQuery;
    }

    public void setInsertQuery(String insertQuery) {
        this.insertQuery = insertQuery;
    }

    public String getUpdateAgeByNameSQL() {
        return updateAgeByNameSQL;
    }

    public void setUpdateAgeByNameSQL(String updateAgeByNameSQL) {
        this.updateAgeByNameSQL = updateAgeByNameSQL;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
//create table
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
    // insertDatabase
    public void insert(String name,int age) {
        jdbcTemplate.update(insertQuery,name,age);
        System.out.println("Created Record Name:"+name+" Age:"+age);
    }
    //Total Record in table student
    public int totalRecord() {
        return jdbcTemplate.execute(new StatementCallback<Integer>() {
            @Override
            public Integer doInStatement(Statement stmt) throws SQLException, DataAccessException {
                ResultSet rs = stmt.executeQuery("select count(*) from student");
                return  rs.next()?rs.getInt(1) :0;
            }
        });
    }
    //Get Student By ResultSet
    private final static class StudentRowMapper implements RowMapper<Student>{


        @Override
        public Student mapRow(ResultSet rs, int i) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setAge(rs.getInt("age"));
            student.setName(rs.getString("name"));
            return student;
        }
    }
    //loadStudent
    public List loadStuddent(String name) {
        return jdbcTemplate.query("SELECT * FROM STUDENT WHERE NAME LIKE '%"+name+"%'",
        new StudentRowMapper());
    }
    //Update
    public void updateAgeByName(String name, int age){
        jdbcTemplate.update(updateAgeByNameSQL,age,name);
    }
    //Delete
    public void DeleteStudent(int id) {
    jdbcTemplate.update(DelQuery,id);
    }

    //Batch Processing
    public int[] add(List<Student> students) {
        List<Object[]> batch = new ArrayList<>();
        students.forEach(student -> batch.add(new Object[]{student.getName(),student.getAge()}));
        return jdbcTemplate.batchUpdate(insertQuery,batch);

    }
    //Programatic
    @Autowired
    private PlatformTransactionManager transactionManager;
    public void save(Object name, Object age) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        String countQuery = "SELECT COUNT(*) FROM STUDENT";
        int total;
        try {
            total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            System.out.println("Before save data, total record is:"+total);
            String sql = "insert into student(name,age) values(?,?)";
            jdbcTemplate.update(sql,name,age);
            total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            System.out.println("After save data, total record is: "+total);

            transactionManager.commit(status);
        }catch (Exception e) {
            transactionManager.rollback(status);
            total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            System.out.println("After rollback,total record is: "+total);

        }
    }


}

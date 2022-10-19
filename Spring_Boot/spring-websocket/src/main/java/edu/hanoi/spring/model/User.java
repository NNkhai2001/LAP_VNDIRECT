package edu.hanoi.spring.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@javax.persistence.Entity
@Table(name = "HN_USER", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {
    private String username;
    private String password;
    private String email;
    private int age;
    private int groupId;

    public User() {
    }

    public User(String username, String password, String email, int age, int groupId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.groupId = groupId;
    }

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "age", nullable = false, length = 100)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "groupId", nullable = false, length = 100)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}

package edu.hanoi.spring.dao;

import edu.hanoi.spring.model.User;

import java.util.List;

public interface UserDAO {
    public String insert(User user);

    public void delete(String name);

    public List<User> listall();

    public User get(String username);

    public void update(User user);
}

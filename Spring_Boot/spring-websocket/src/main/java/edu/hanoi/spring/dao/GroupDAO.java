package edu.hanoi.spring.dao;

import edu.hanoi.spring.model.Group;
import edu.hanoi.spring.model.User;

import java.util.List;

public interface GroupDAO {
    public List<Group> list();

    String insert(Group group);

    void update(Group group);

    void delete(int id);

    Group get(int id);
}

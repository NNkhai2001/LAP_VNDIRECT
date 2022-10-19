package edu.hanoi.spring.dao.Impl;

import edu.hanoi.spring.dao.GroupDAO;
import edu.hanoi.spring.model.Group;
import edu.hanoi.spring.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Group> list() {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery(" from Group");
        try {
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public String insert(Group group) {
        Session session = sessionFactory.getObject().openSession();
        try {
            Transaction tran = session.beginTransaction();
            session.save(group);
            session.flush();
            tran.commit();
            return "Save group " + group.getName() + " Success!";
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Group group) {
        Session session = sessionFactory.getObject().openSession();
        Transaction tran = session.beginTransaction();
        try {
            group = (Group) session.merge(group);
            session.save(group);
            session.flush();
            tran.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getObject().openSession();
        Transaction tran = session.beginTransaction();
        try {
            Group group = session.get(Group.class, id);
            if (group != null) session.delete(group);
            session.flush();
            tran.commit();
        } finally {
            session.close();
            ;
        }
    }

    @Override
    public Group get(int id) {
        Session session = sessionFactory.getObject().openSession();
        Group group = session.get(Group.class, id);
        return group;

    }

}


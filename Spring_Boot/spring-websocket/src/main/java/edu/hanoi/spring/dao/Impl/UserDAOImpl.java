package edu.hanoi.spring.dao.Impl;

import edu.hanoi.spring.dao.UserDAO;
import edu.hanoi.spring.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("userDAO")
public class UserDAOImpl implements UserDAO {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<User> listall() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from User order by age desc ");
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public String insert(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            Transaction tran = session.beginTransaction();
            session.save(user);
            session.flush();
            tran.commit();
            return "Save user " + user.getUsername() + " Success!";
        } finally {
            session.close();
        }
    }

    @Override
    public User get(String username) {
        Session session = sessionFactory.getObject().openSession();
        User user = session.get(User.class, username);
        return user;
    }

    @Override
    public void delete(String name) {
        Session session = sessionFactory.getObject().openSession();
        Transaction tran = session.beginTransaction();
        try {
            User user = session.get(User.class, name);
            if (user != null) session.delete(user);
            session.flush();
            tran.commit();
        } finally {
            session.close();
            ;
        }
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getObject().openSession();
        Transaction tran = session.beginTransaction();
        try {
            user = (User) session.merge(user);
            session.save(user);
            session.flush();
            tran.commit();
        } finally {
            session.close();
            ;
        }
    }
}

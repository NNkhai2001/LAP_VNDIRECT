package edu.hanoi.service.dao.Impl;

import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.User;
import org.apache.log4j.Logger;
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
//    private final static Logger LOGGER ;

    @Autowired
//    @Qualifier=("sessionFactory")
    private LocalSessionFactoryBean sessionFactory;


    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> userList() {
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
            Serializable value = session.save(user);
            session.flush();
            System.out.println("Save user " + user.getUsername() + " done!" + value);
            tran.commit();
            return value.toString();
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
        try{
          User user = session.get(User.class,name);
          if(user != null) session.delete(user);
          session.flush();
          tran.commit();
        } finally {
            session.close();;
        }
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getObject().openSession();
        Transaction tran = session.beginTransaction();
        try{
             user =(User)session.merge(user);
           session.save(user);
            session.flush();
            tran.commit();
        } finally {
            session.close();;
        }
    }
}

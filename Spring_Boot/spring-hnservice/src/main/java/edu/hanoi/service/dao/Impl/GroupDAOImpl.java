package edu.hanoi.service.dao.Impl;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.service.model.Group;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("groupDAO")

public class GroupDAOImpl implements GroupDAO {

    @Autowired
//    @Qualifier=("sessionFactory")
    private LocalSessionFactoryBean sessionFactory;


    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Group> list() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from Group ");
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }
}

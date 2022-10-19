package edu.hanoi.jazz.service;

import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class JazzConnectionSignUp implements ConnectionSignUp {
    @Autowired
    UserDAO userDAO;

    @Override
    public String execute(final Connection<?> connection) {
        UserProfile userProfile = connection.fetchUserProfile();
        System.out.println("--------->id " + userProfile.getId() + " name " + userProfile.getUsername());
        User user = userDAO.get(userProfile.getUsername());
        if (user != null) return user.getUsername();
        User user1 = new User();
        user1.setUsername(userProfile.getUsername());
        user1.setPassword("123");
        user1.setGroupId(1);
        user1.setEmail(userProfile.getEmail());
        userDAO.insert(user1);

        return user1.getUsername();

    }
}

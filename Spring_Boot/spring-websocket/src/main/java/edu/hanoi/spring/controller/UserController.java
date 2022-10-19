package edu.hanoi.spring.controller;

import edu.hanoi.spring.dao.UserDAO;
import edu.hanoi.spring.model.Group;
import edu.hanoi.spring.model.Message;
import edu.hanoi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDAO userDAO;

    @MessageMapping("/user")
    @SendTo("/topic/chat")
    public Message add(User user, Message name) {
        String status = null;
        if (name.getContent() == null) {
            status = userDAO.insert(user);
            System.out.println("user: " + user.getUsername() + " - email: " + user.getEmail() + "- groupId" + user.getGroupId());

        } else {
            userDAO.update(user);
            status = "Update user "+user.getUsername() +" successful!";
        }
        return new Message(status);
    }

    //Get Group by Id
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUser() {
        return userDAO.listall();
    }

    @MessageMapping("/user/delete/{username}")
    @SendTo("/topic/chat")
    public Message deleteUser(@DestinationVariable String username) {
//        System.out.println("username: " + username);
        userDAO.delete(username);
        return new Message("Delete successful!");
    }

    @RequestMapping("updateUser/{username}")
    public User UpdateUser(@PathVariable String username) {
        System.out.println("username: " + username);
        return userDAO.get(username);
    }

    @RequestMapping(value = "getuser/{username}",method = RequestMethod.GET)
    public User getUserbyusername(@PathVariable String username) {
        return userDAO.get(username);
    }

}

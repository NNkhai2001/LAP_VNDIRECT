package edu.hanoi.spring.controller;

import edu.hanoi.spring.dao.GroupDAO;
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
public class GroupController {
    @Autowired
    GroupDAO groupDAO;

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<Group> list() {
        return groupDAO.list();
    }

    @MessageMapping("/group")
    @SendTo("/topic/chat")
    public Message add(Group group, Message name) {
        String status = null;

        if (name.getContent() == null) {
            status = groupDAO.insert(group);
            System.out.println("user: " + group.getName() + "- groupId" + group.getId());

        } else {
            groupDAO.update(group);
            status = "Update group " + group.getName() + " successful!";
        }
        return new Message(status);
    }


    @MessageMapping("/group/delete/{id}")
    @SendTo("/topic/chat")
    public Message deleteGroup(@DestinationVariable String id) {
        int Id = Integer.parseInt(id);
//        System.out.println("username: " + username);
        groupDAO.delete(Id);
        return new Message("Delete successful!");
    }

    @RequestMapping("updateGroup/{id}")
    public Group UpdateUser(@PathVariable String id) {
        int Id = Integer.parseInt(id);
        System.out.println("Group Id: " + id);
        return groupDAO.get(Id);
    }

    @RequestMapping(value = "getgroup/{id}", method = RequestMethod.GET)
    public Group getUserbyusername(@PathVariable String id) {
        int Id = Integer.parseInt(id);
        return groupDAO.get(Id);
    }
}
package edu.hanoi.service.controller;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.Group;
import edu.hanoi.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@RestController

public class UserRestServiceController {
    Logger LOGGER = Logger.getLogger(String.valueOf(UserRestServiceController.class));
    @Autowired
    UserDAO userDAO;
    @Autowired
    GroupDAO groupDAO;

    //    @RequestMapping(value = "/list/users", method = RequestMethod.GET)
//    public List<User> listUser() {
//        return userDAO.userList();
//    }
    @RequestMapping(value = "/list/users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @PostFilter("hasPermission(filterObject,'read')")
    public List<User> listUser() {
//        if (!request.isUserInRole("ROLE_ADMIN")) {
//            throw new RuntimeException("Access Denied!");
//        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("\n---------------------> " + auth.getAuthorities());
        return userDAO.userList();
    }


    @RequestMapping(value = "list/groups", method = RequestMethod.GET)
    public Group[] listGroups() {
        return groupDAO.list().toArray(new Group[0]);
    }


    @RequestMapping(value = "add/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user) {
        return userDAO.insert(user);
    }


    @RequestMapping(value = "get/user/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable String name) {
        return userDAO.get(name);
    }

    @RequestMapping(value = "delete/user/{name}", method = RequestMethod.GET)
    public void delUser(@PathVariable String name) {
        userDAO.delete(name);
    }

    @RequestMapping(value = "update/user", method = RequestMethod.POST)
    public void updateUser(@RequestBody User user) {
        userDAO.update(user);
    }
}

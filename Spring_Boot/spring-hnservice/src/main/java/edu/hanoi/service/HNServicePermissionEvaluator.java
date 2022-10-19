package edu.hanoi.service;

import edu.hanoi.service.controller.UserRestServiceController;
import edu.hanoi.service.model.User;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.logging.Logger;

@Component
public class HNServicePermissionEvaluator implements PermissionEvaluator {
    Logger LOGGER = Logger.getLogger(String.valueOf(HNServicePermissionEvaluator.class));

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
//        if (targetDomainObject instanceof User) {
//            User user = (User) targetDomainObject;
//            return user.getAge() < 20;
//        }
        LOGGER.info("--------> " + targetDomainObject + " :" + permission);
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        LOGGER.info("----------->" + targetType + " :" + permission);
        return true;
    }


}

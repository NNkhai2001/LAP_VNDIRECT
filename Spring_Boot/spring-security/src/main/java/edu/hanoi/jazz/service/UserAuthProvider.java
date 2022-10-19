package edu.hanoi.jazz.service;

import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthProvider implements AuthenticationProvider {
    @Autowired
    UserDAO userDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        System.out.println(username);
        User user = userDAO.get(username);
        if (user == null) return null;
        System.out.println("-------------------->Found " + user + " by " + username);
        if (!user.getPassword().equals(authentication.getCredentials())) return null;
        return successful(username,authentication.getCredentials().toString(),"{noop}"+user.getPassword());
    }
    private UsernamePasswordAuthenticationToken successful(String username, String toString, String password){
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(username,password,grantedAuths);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

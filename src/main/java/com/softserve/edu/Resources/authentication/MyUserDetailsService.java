package com.softserve.edu.Resources.authentication;

import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.Role;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userService.getUserForSpring(email);
        //User user = getUserForSpring(email);

        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRole()));
    }

    private final Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return getGrantedAuthorities(getPrivileges(role));
    }

    private final List<String> getPrivileges(Role role) {
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> collection = new ArrayList<Privilege>();

        collection.addAll(role.getPrivileges());

        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        privileges.add(role.getName());

        return privileges;
    }

    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    //should be this service method here or be replaced to another service class?
    /*@Transactional
    User getUserForSpring (String email){
        System.out.println(1);
        User user = userDAO.findByEmail(email);
        System.out.println(2);
        Role role = user.getRole();
        System.out.println(3);

        //To start lazy initialization
        ArrayList<Privilege> privileges = new ArrayList<>(role.getPrivileges());
        System.out.println(4);
        return user;
    }*/
}
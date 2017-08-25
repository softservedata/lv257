package edu.softserve.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.softserve.dao.RoleDAO;
import edu.softserve.dao.UserDAO;
import edu.softserve.entity.Privilege;
import edu.softserve.entity.Role;
import edu.softserve.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userDAO.findByEmail(username);


        for (GrantedAuthority grantedAuthority: myGrantedAuthorities()) {
            System.out.println("1   -   " + myGrantedAuthorities());
        }

       System.out.println("HAAALOOOOOOOOOOOOOOOOOO!!!!!!!!!!!");




        System.out.println("User = " + user);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;




        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked, myGrantedAuthorities());

    }

    private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private final List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> collection = new ArrayList<Privilege>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private final Collection<? extends GrantedAuthority> myGrantedAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("READ_PRIVILEGE"));
        authorities.add(new SimpleGrantedAuthority("WRITE_PRIVILEGE"));
        authorities.add(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authorities;
    }

}
package com.softserve.edu.Resources.entity;

import com.softserve.edu.Resources.Constants;
import org.jboss.aerogear.security.otp.api.Base32;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Column(name = "email", length = 36, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    private String secret;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private UserDetails userDetails;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private VerificationToken verificationToken;

    @OneToMany(mappedBy = "resourcesAdmin")
    private Collection<ResourceRequest> requestsByAdmin;

    @OneToMany(mappedBy = "register")
    private Collection<ResourceRequest> requestsByRegister;

    public User() {
        this.secret = Base32.random();
        this.enabled = false;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public User setRoles(Collection<Role> roles) {
        this.roles = roles;
        return this;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public User setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        userDetails.setUser(this);
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public User setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public User setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
        return this;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        if (!username.equals(user.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", secret='" + secret + '\'' +
                ", roles=" + roles +
                ", userDetails=" + userDetails +
                ", verificationToken=" + verificationToken +
                ", requestsByAdmin=" + requestsByAdmin +
                ", requestsByRegister=" + requestsByRegister +
                '}';
    }
}
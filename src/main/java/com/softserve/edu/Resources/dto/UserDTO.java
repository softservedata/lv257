package com.softserve.edu.Resources.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotEmpty(message = "Email should not be empty")
    @NotNull
    @Email(message = "Invalid email address!")
    private String email;

    @NotNull
    @Size(min = 3, max = 15, message = "Password should be from 3 to 15 characters")
    private String password;

    @Transient
    @NotEmpty(message = "Confirm Password should not be empty")
    @NotNull
    private String confirmPassword;

    private boolean isUsing2FA;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(final Integer role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(boolean isUsing2FA) {
        this.isUsing2FA = isUsing2FA;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [password=").append(password).append(", confirmPassword=").append(confirmPassword).append(", email=").append(email).append(", isUsing2FA=")
                .append(isUsing2FA).append(", role=").append(role).append("]");
        return builder.toString();
    }

}
package com.softserve.edu.Resources.util;

public class RegistrationConfirmMail extends Mail {
    String host;
    String token;
    public RegistrationConfirmMail(String receiver){
        super(receiver,
                "Registration Confirmation",
                "registrationConfirmation.vm");
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

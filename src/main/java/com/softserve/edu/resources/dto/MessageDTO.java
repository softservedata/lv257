package com.softserve.edu.resources.dto;


import com.softserve.edu.resources.entity.User;

/**
 * Represent message that will be sent on e-mail from resource administrator to register
 */
public class MessageDTO {

    private String content;
    private String theme;
    private User sender;
    private User receiver;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}

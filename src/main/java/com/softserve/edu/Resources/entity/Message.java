package com.softserve.edu.Resources.entity;

public class Message {
    private String theme;
    private  Purpose purpose;
    private String comment;

    public enum Purpose{
        Decline, Accept, Refinement
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

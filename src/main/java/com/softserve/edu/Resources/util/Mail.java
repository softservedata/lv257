package com.softserve.edu.Resources.util;


public abstract class Mail {

    protected String receiver;
    protected String subject;
    protected String templateName;

    public Mail(String receiver, String subject, String templateName) {
        this.receiver = receiver;
        this.subject = subject;
        this.templateName = templateName;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getTemplateName() {
        return templateName;
    }


}

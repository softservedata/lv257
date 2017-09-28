package com.softserve.edu.Resources.service;

public interface MessageHandler {
    String getSubject();

    String getSender();

    String getReceiver();

    String getContent();
}

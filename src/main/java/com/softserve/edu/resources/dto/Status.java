package com.softserve.edu.resources.dto;

/**
 * Created by User on 25.08.2017.
 */
public enum Status {
    NEW, ACCEPTED, DECLINED;

    static Status getStatus(String statusInfo) {
        Status status = null;
        if (NEW.toString().equalsIgnoreCase(statusInfo)) {
            status = NEW;
        } else if (ACCEPTED.toString().equalsIgnoreCase(statusInfo)) {
            status = ACCEPTED;
        } else if (DECLINED.toString().equalsIgnoreCase(statusInfo)) {
            status = DECLINED;
        }
        return status;
    }
}

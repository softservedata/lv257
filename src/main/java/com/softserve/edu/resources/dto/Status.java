package com.softserve.edu.resources.dto;

/**
 * Needed for tracking request state
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

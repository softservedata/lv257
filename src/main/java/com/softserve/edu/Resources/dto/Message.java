package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ResourceRequest;

public class Message {
    private String theme;
    private  Purpose purpose;
    private String comment;

    public enum Purpose{
        Decline{
            @Override
            public ResourceRequest.Status getNeededStatus() {
                return ResourceRequest.Status.DECLINED;
            }
        },
        Accept{
            @Override
            public ResourceRequest.Status getNeededStatus() {
                return ResourceRequest.Status.ACCEPTED;
            }
        },
        Refinement{
            @Override
            public ResourceRequest.Status getNeededStatus() {
                return ResourceRequest.Status.TO_REFINEMENT;
            }
        };

        public abstract ResourceRequest.Status getNeededStatus();
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

    public ResourceRequest.Status getRequestStatus(){
        return purpose.getNeededStatus();
    }
}

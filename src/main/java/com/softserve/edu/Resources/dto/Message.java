package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ResourceRequest;

public class Message {

    private long id_request;
    private Purpose purpose;
    private String comment;

    public enum Purpose {
        Decline {
            @Override
            public ResourceRequest.Status getNeededStatus() {
                return ResourceRequest.Status.DECLINED;
            }

            @Override
            public String getMessageContent() {
                return "Your request was declined.\n";
            }
        },
        Accept {
            @Override
            public ResourceRequest.Status getNeededStatus() {
                return ResourceRequest.Status.ACCEPTED;
            }

            @Override
            public String getMessageContent() {
                return "Your request was successfully accepted.\n";
            }
        },
        Refinement {
            @Override
            public ResourceRequest.Status getNeededStatus() {
                return ResourceRequest.Status.TO_REFINEMENT;
            }

            @Override
            public String getMessageContent() {
                return "Your request needed more information.\n";
            }
        };

        public abstract ResourceRequest.Status getNeededStatus();

        public abstract String getMessageContent();
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

    public long getId_request() {
        return id_request;
    }

    public void setId_request(long id_request) {
        this.id_request = id_request;
    }

    public ResourceRequest.Status getRequestStatus() {
        return purpose.getNeededStatus();
    }

    public Message(long id_request, Purpose purpose, String comment) {
        this.id_request = id_request;
        this.purpose = purpose;
        this.comment = comment;
    }

    public Message() {
    }



    @Override
    public String toString() {
        return "Message{" +
                "id_request=" + id_request +
                ", purpose=" + purpose +
                ", comment='" + comment + '\'' +
                '}';
    }
}

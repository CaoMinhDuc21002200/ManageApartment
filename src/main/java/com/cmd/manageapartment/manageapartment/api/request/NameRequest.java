package com.cmd.manageapartment.manageapartment.api.request;

public class NameRequest {
    private String fullname;

    // Constructor, getters, and setters
    public NameRequest() {}

    public NameRequest(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}

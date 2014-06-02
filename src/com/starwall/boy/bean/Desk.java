package com.starwall.boy.bean;

/**
 * Created by Antony on 14-5-30.
 */
public class Desk {
    private String name;
    private int valid;
    private int capacity;
    private int type;
    private int senderId;
    private boolean supportPaddling;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public boolean isSupportPaddling() {
        return supportPaddling;
    }

    public void setSupportPaddling(boolean supportPaddling) {
        this.supportPaddling = supportPaddling;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


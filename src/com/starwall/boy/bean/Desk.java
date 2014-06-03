package com.starwall.boy.bean;

import com.starwall.boy.AppException;
import com.starwall.boy.common.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Antony on 14-5-30.
 */
public class Desk {

    private String _id;
    private String name;
    private int valid;
    private int capacity;
    private int type;
    private int senderId;
    private boolean supportPaddling;
    private Service service;

    public static Desk parseJson(JSONObject json) throws JSONException {

        Desk desk = new Desk();
        desk.setName(json.getString("name"));
        desk.set_id(json.getString("_id"));
        if (json.has("service")) {
            desk.setService(Service.parseJson(json.getJSONObject("service")));
        }
        return desk;
    }

    public static Desk parse(InputStream inputStream) throws IOException, AppException, JSONException {

        String json = StringUtils.toConvertString(inputStream);

        JSONObject jsonObject = new JSONObject(json).getJSONObject("data");
        Desk desk = new Desk();

        return desk;
    }

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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}


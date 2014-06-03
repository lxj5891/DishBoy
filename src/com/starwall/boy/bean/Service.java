package com.starwall.boy.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Antony on 14-6-3.
 */
public class Service {

    private String deskId;
    private int people;
    private int type;
    private int status;
    private int unfinishedCount;
    private String createat;
    private String _id;

    public static Service parseJson(JSONObject json) throws JSONException {
        Service service = new Service();
        service.set_id(json.getString("_id"));
        service.setType(json.getInt("type"));
        service.setStatus(json.getInt("status"));
        service.setUnfinishedCount(json.getInt("unfinishedCount"));
        return service;
    }

    public String getDeskId() {
        return deskId;
    }

    public void setDeskId(String deskId) {
        this.deskId = deskId;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUnfinishedCount() {
        return unfinishedCount;
    }

    public void setUnfinishedCount(int unfinishedCount) {
        this.unfinishedCount = unfinishedCount;
    }

    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}

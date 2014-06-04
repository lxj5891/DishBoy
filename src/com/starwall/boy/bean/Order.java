package com.starwall.boy.bean;

import org.json.JSONException;
import org.json.JSONObject;


public class Order {

    private String deskId;
    private String serviceId;
    private String userId;
    private String itemId;
    private int orderSeq;
    private int orderNum;
    private Desk desk;
    private Service service;
    private Item item;
    private int itemType;
    private int type;
    private int back;
    private String remark;
    private String amount;
    private String backAmount;
    private String amountNum;
    private int amountPrice;
    private String willBackAmount;
    private String isNew;
    private String totalBackAmount;
    private int discount;
    private int hasBack;

    public static Order parseJson(JSONObject json) throws JSONException {

        Order order = new Order();

        return order;
    }

    public String getDeskId() {
        return deskId;
    }

    public void setDeskId(String deskId) {
        this.deskId = deskId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(String backAmount) {
        this.backAmount = backAmount;
    }

    public String getAmountNum() {
        return amountNum;
    }

    public void setAmountNum(String amountNum) {
        this.amountNum = amountNum;
    }

    public int getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(int amountPrice) {
        this.amountPrice = amountPrice;
    }

    public String getWillBackAmount() {
        return willBackAmount;
    }

    public void setWillBackAmount(String willBackAmount) {
        this.willBackAmount = willBackAmount;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getTotalBackAmount() {
        return totalBackAmount;
    }

    public void setTotalBackAmount(String totalBackAmount) {
        this.totalBackAmount = totalBackAmount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getHasBack() {
        return hasBack;
    }

    public void setHasBack(int hasBack) {
        this.hasBack = hasBack;
    }
}

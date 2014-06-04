package com.starwall.boy.bean;

import com.starwall.boy.AppException;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Antony on 14-6-4.
 */
public class OrderList {

    private ArrayList<Order> items;

    public static OrderList parse(InputStream inputStream) throws IOException, AppException, JSONException {

        OrderList orderList = new OrderList();

        return orderList;
    }
}

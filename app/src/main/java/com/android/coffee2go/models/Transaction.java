package com.android.coffee2go.models;

import java.util.ArrayList;

public class Transaction {
    private ArrayList<OrderLine> orderLines;
    private int accountId;

    public Transaction() {
        orderLines = new ArrayList<>();
    }

    public void addOrderLine(OrderLine orderLine){
        orderLines.add(orderLine);
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public int getAccountId() {
        return accountId;
    }
}

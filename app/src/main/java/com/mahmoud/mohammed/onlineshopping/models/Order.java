package com.mahmoud.mohammed.onlineshopping.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mohammed on 12/12/2017.
 */

public class Order implements Parcelable {
    String orderId;
    String customerId;
    String customerAddress;
    String orderDate;
    double totalCost;

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Order(double totalCost,String orderId, String customerId, String customerAddress, String orderDate) {
        this.customerId = customerId;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.orderId=orderId;
        this.totalCost=totalCost;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Order() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderId);
        dest.writeString(this.customerId);
        dest.writeString(this.customerAddress);
        dest.writeString(this.orderDate);
        dest.writeDouble(this.totalCost);
    }

    protected Order(Parcel in) {
        this.orderId = in.readString();
        this.customerId = in.readString();
        this.customerAddress = in.readString();
        this.orderDate = in.readString();
        this.totalCost = in.readDouble();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}

package com.mahmoud.mohammed.onlineshopping.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mohammed on 28/11/2017.
 */

public class Product implements Parcelable {
    private String ProductName;
    private String Price;
    private int id;
    private double quantity;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    private String Description;

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    private String ImageUrl;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ProductName);
        dest.writeString(this.Price);
        dest.writeInt(this.id);
        dest.writeDouble(this.quantity);
        dest.writeString(this.Description);
        dest.writeString(this.ImageUrl);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.ProductName = in.readString();
        this.Price = in.readString();
        this.id = in.readInt();
        this.quantity = in.readDouble();
        this.Description = in.readString();
        this.ImageUrl = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}

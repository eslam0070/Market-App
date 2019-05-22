package com.android.newvolley.Models;

import com.android.newvolley.Models.Reviews;

import java.util.ArrayList;

public class Data {
    String id , name , imgUrl , companyId;
    int price , type , gender;
    ArrayList<Reviews> reviewsArrayList;


    public ArrayList<Reviews> getReviewsArrayList() {
        return reviewsArrayList;
    }

    public void setReviewsArrayList(ArrayList<Reviews> reviewsArrayList) {
        this.reviewsArrayList = reviewsArrayList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}

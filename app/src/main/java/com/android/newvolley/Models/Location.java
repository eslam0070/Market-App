package com.android.newvolley.Models;

import java.util.ArrayList;

public class Location {
    private int id;
    private String Name;
    private ArrayList<Branch> branchArrayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Branch> getBranchArrayList() {
        return branchArrayList;
    }

    public void setBranchArrayList(ArrayList<Branch> branchArrayList) {
        this.branchArrayList = branchArrayList;
    }
}

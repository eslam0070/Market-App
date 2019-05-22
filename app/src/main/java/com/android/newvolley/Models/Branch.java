package com.android.newvolley.Models;

import com.google.android.gms.maps.model.LatLng;

public class Branch {
    private String addresse;
    private double Long;
    private  double lat;

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}

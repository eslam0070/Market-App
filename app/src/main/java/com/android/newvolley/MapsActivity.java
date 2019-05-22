package com.android.newvolley;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.android.newvolley.Classes.VollySingletone;
import com.android.newvolley.Models.Branch;
import com.android.newvolley.Models.Location;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        String UrlMap = "https://api.myjson.com/bins/130t4t";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, UrlMap,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Location> locationArrayList = new ArrayList<>();
                ArrayList<Branch> branchArrayList = new ArrayList<>();

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Location location = new Location();
                        location.setId(jsonObject.getInt("id"));
                        location.setName(jsonObject.getString("name"));
                        JSONArray jsonArray1 = jsonObject.getJSONArray("branches");
                        for (int u = 1; u < jsonArray1.length(); u++) {
                            Branch branch = new Branch();
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(u);
                            branch.setAddresse(jsonObject1.getString("address"));
                            branch.setLat(jsonObject1.getDouble("lat"));
                            branch.setLong(jsonObject1.getDouble("lng"));
                            branchArrayList.add(branch);
                        }
                        location.setBranchArrayList(branchArrayList);
                        locationArrayList.add(location);
                    }

                } catch (JSONException e){
                    e.getMessage();
                }

                for (int u = 1; u < branchArrayList.size(); u++) {
                    LatLng sydney = new LatLng(branchArrayList.get(u).getLat(), branchArrayList.get(u).getLong());
                    mMap.addMarker(new MarkerOptions().position(sydney).title(branchArrayList.get(u).getAddresse()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollySingletone.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }
}

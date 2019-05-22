package com.android.newvolley.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.newvolley.Classes.VollySingletone;
import com.android.newvolley.Models.Data;
import com.android.newvolley.Models.Reviews;
import com.android.newvolley.R;
import com.android.newvolley.ViewHolder.Adapter;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DataFragment extends Fragment {

    RecyclerView mRecyclerView;
    Adapter mAdapter;
    View view;
    public DataFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data, container, false);
        mRecyclerView = view.findViewById(R.id.recyc);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LoadJson();
        return view;
    }

    private void LoadJson() {
        String url = "https://api.myjson.com/bins/e0lq5";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Data> dataVollies = new ArrayList<>();

                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0 ; i<jsonArray.length() ; i ++)
                    {
                        Data data = new Data();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        data.setId(jsonObject1.getString("id"));
                        data.setName(jsonObject1.getString("name"));
                        data.setImgUrl(jsonObject1.getString("imgUrl"));
                        data.setPrice(jsonObject1.getInt("price"));
                        data.setType(jsonObject1.getInt("type"));
                        data.setGender(jsonObject1.getInt("gender"));
                        data.setCompanyId(jsonObject1.getString("companyId"));
                        JSONArray jsonArray1 =jsonObject1.getJSONArray("reviews");

                        ArrayList<Reviews>reviewsArrayList = new ArrayList<>();

                        for (int y = 0 ; y<jsonArray1.length() ; y++)
                        {
                            JSONObject jsonObject = jsonArray1.getJSONObject(y);
                            Reviews reviews = new Reviews();
                            reviews.setUsername(jsonObject.getString("username"));
                            reviews.setRate(jsonObject.getDouble("rate"));
                            reviewsArrayList.add(reviews);

                        }
                        data.setReviewsArrayList(reviewsArrayList);
                        dataVollies.add(data);

                    }
                    mAdapter = new Adapter(getContext(),dataVollies);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VollySingletone.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }
}

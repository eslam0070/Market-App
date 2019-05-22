package com.android.newvolley.Classes;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VollySingletone {
    @SuppressLint("StaticFieldLeak")
    private static VollySingletone instance;
    private RequestQueue requestQueue;
    @SuppressLint("StaticFieldLeak")
    private static Context ctx;

    private VollySingletone(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized VollySingletone getInstance(Context context) {
        if (instance == null) {
            instance = new VollySingletone(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}

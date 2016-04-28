package com.babolforfun.volley.requestvolley.controllers;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton  volleySingleton;
    private RequestQueue            requestQueue;
    private static Context          ctx;

    private VolleySingleton(Context context) {

        ctx          = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (volleySingleton == null)
            volleySingleton = new VolleySingleton(context);

        return volleySingleton;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}

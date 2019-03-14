package com.mauscoelho.researcherroutes.settings;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class App extends Application {

    private static App sInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        Volley();
    }

    private void Volley() {
        mRequestQueue = Volley.newRequestQueue(this);
        sInstance = this;
    }

    public synchronized static App getsInstance(){
        return sInstance;
    }

    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }

}

package com.mauscoelho.researcherroutes.network.services;


import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.mauscoelho.researcherroutes.App;
import com.mauscoelho.researcherroutes.network.Endpoints;
import com.mauscoelho.researcherroutes.network.UtilityHelper;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.Stop;
import com.mauscoelho.researcherroutes.network.models.Time;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class RouteService {

    private Gson gson;
    private UtilityHelper utilityHelper;

    @Inject
    public RouteService(Gson gson, UtilityHelper utilityHelper) {
        this.gson = gson;
        this.utilityHelper = utilityHelper;
    }

    public void getRoutes(final IAction<Route[]> callback, String stopName) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                Endpoints.FIND_ROUTES_BY_STOPNAME,
                utilityHelper.getJsonObjectByStopName(stopName),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = utilityHelper.getJsonArray(jsonObject);
                        Route[] routes = gson.fromJson(jsonArray.toString(), Route[].class);
                        callback.OnCompleted(routes);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.OnError(null);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getMapHeaders();
            }
        };

        App.getsInstance().getmRequestQueue().add(request);
    }



    public void getStops(final IAction<Stop[]> callback, int routeId) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                Endpoints.FIND_STOPS_BY_ROUTEID,
                utilityHelper.getJsonRouteId(routeId),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = utilityHelper.getJsonArray(jsonObject);
                        Stop[] stops = gson.fromJson(jsonArray.toString(), Stop[].class);
                        callback.OnCompleted(stops);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.OnError(null);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getMapHeaders();
            }
        };

        App.getsInstance().getmRequestQueue().add(request);
    }

    public void getTimes(final IAction<Time[]> callback, int routeId) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                Endpoints.FIND_DEPARTURES_BY_ROUTEID,
                utilityHelper.getJsonRouteId(routeId),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = utilityHelper.getJsonArray(jsonObject);
                        Time[] times = gson.fromJson(jsonArray.toString(), Time[].class);
                        callback.OnCompleted(times);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.OnError(null);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getMapHeaders();
            }
        };

        App.getsInstance().getmRequestQueue().add(request);
    }


    @NonNull
    private Map<String, String> getMapHeaders() {
        HashMap<String, String> params = new HashMap<>();
        params.put("X-AppGlu-Environment", "staging");
        params.put("Authorization", "Basic V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==");
        return params;
    }


}

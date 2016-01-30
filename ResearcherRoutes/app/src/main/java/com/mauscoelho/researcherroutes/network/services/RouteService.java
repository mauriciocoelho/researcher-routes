package com.mauscoelho.researcherroutes.network.services;


import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mauscoelho.researcherroutes.App;
import com.mauscoelho.researcherroutes.network.Endpoints;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.models.Time;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.Stop;
import com.mauscoelho.researcherroutes.network.parsers.RouteParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class RouteService {

    @Inject
    public RouteService() {

    }

    public void findRoutesByStopName(final IAction<List<Route>> callback, String stopName){
        JSONObject jsonObject = null;

        try {
            jsonObject = getJsonObjectStopName(stopName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.FIND_ROUTES_BY_STOPNAME, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        RouteParser routeParser = new RouteParser();
                        List<Route> routes = routeParser.findRoutesByStopName(jsonObject);
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

    public void findStopsByRouteId(final IAction<List<Stop>> callback, int routeId){
        JSONObject jsonObject = null;

        try {
            jsonObject = getJsonObjectStopsByRoute(routeId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.FIND_STOPS_BY_ROUTEID, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        RouteParser routeParser = new RouteParser();
                        List<Stop> routes = routeParser.findStopsByRouteId(jsonObject);

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

    public void findDeparturesByRouteId(final IAction<List<Time>> callback, int routeId){
        JSONObject jsonObject = null;

        try {
            jsonObject = getJsonObjectDeparturesByRoute(routeId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.FIND_DEPARTURES_BY_ROUTEID, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        RouteParser routeParser = new RouteParser();
                        List<Time> time = routeParser.findDeparturesByRouteId(jsonObject);

                        callback.OnCompleted(time);
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
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("X-AppGlu-Environment", "staging");
        params.put("Authorization", "Basic V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==");
        return params;
    }

    @NonNull
    private JSONObject getJsonObjectStopName(String stopName) throws JSONException {
        return new JSONObject("{\n" +
                "\"params\": {\n" +
                "\"stopName\": \"%" + stopName + "%\"\n" +
                "}\n" +
                "}");
    }

    @NonNull
    private JSONObject getJsonObjectStopsByRoute(int routeId) throws JSONException {
        return new JSONObject("{\n" +
                "\"params\": {\n" +
                "\"routeId\":" + routeId + "\n" +
                "}\n" +
                "}");
    }

    @NonNull
    private JSONObject getJsonObjectDeparturesByRoute(int routeId) throws JSONException {
        return new JSONObject("{\n" +
                "\"params\": {\n" +
                "\"routeId\":" + routeId + "\n" +
                "}\n" +
                "}");
    }

}

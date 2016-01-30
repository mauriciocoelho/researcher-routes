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
import com.mauscoelho.researcherroutes.network.parsers.ParserCommand;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class RouteService {

    public static final String STOP_NAME = "stopName";
    public static final String ROUTE_ID = "routeId";

    ParserCommand parserCommand;

    @Inject
    public RouteService(ParserCommand parserCommand) {
        this.parserCommand = parserCommand;
    }

    public void getRoutes(final IAction<List<Route>> callback, String stopName) {
        JSONObject jsonObject = getJsonObject(STOP_NAME, stopName);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.FIND_ROUTES_BY_STOPNAME, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        List<Route> routes = parserCommand.executeRoutes(jsonObject);
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

    public void getStops(final IAction<List<Stop>> callback, int routeId) {
        JSONObject jsonObject = getJsonRouteId(ROUTE_ID, String.valueOf(routeId));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.FIND_STOPS_BY_ROUTEID, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        List<Stop> routes = parserCommand.executeStops(jsonObject);
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

    public void getTimes(final IAction<List<Time>> callback, int routeId) {
        JSONObject jsonObject = getJsonRouteId(ROUTE_ID, String.valueOf(routeId));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.FIND_DEPARTURES_BY_ROUTEID, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        List<Time> time = parserCommand.executeTimes(jsonObject);
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
    private JSONObject getJsonObject(String param, String value)  {
        try {
            return new JSONObject("{\n" +
                    "\"params\": {\n" +
                    "\"" + param + "\": \"%" + value + "%\"\n" +
                    "}\n" +
                    "}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @NonNull
    private JSONObject getJsonRouteId(String param, String value)  {
        try {
            return new JSONObject("{\n" +
                    "\"params\": {\n" +
                    "\"" + param + "\": "+ value +" \n" +
                    "}\n" +
                    "}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

}

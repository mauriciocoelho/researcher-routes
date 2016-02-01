package com.mauscoelho.researcherroutes.network.services;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mauscoelho.researcherroutes.network.parsers.RouteParser;
import com.mauscoelho.researcherroutes.network.parsers.StopsParser;
import com.mauscoelho.researcherroutes.network.parsers.TimesParser;
import com.mauscoelho.researcherroutes.settings.App;
import com.mauscoelho.researcherroutes.network.util.Endpoints;
import com.mauscoelho.researcherroutes.settings.JsonObjectWithAuthRequest;
import com.mauscoelho.researcherroutes.network.util.UtilityHelper;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.Stop;
import com.mauscoelho.researcherroutes.network.models.Time;

import org.json.JSONObject;

import javax.inject.Inject;

public class RouteService {

    private UtilityHelper utilityHelper;
    private RouteParser routeParser;
    private StopsParser stopsParser;
    private TimesParser timesParser;

    @Inject
    public RouteService(UtilityHelper utilityHelper, RouteParser routeParser, StopsParser stopsParser, TimesParser timesParser) {
        this.utilityHelper = utilityHelper;
        this.routeParser= routeParser;
        this.stopsParser = stopsParser;
        this.timesParser = timesParser;
    }

    public void getRoutes(final IAction<Route[]> callback, String stopName) {
        JsonObjectWithAuthRequest request = new JsonObjectWithAuthRequest(Request.Method.POST,
                Endpoints.FIND_ROUTES_BY_STOPNAME,
                utilityHelper.getJsonByStopName(stopName),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        callback.OnCompleted(routeParser.convertToArray(jsonObject));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.OnError(null);
            }
        });
        App.getsInstance().getmRequestQueue().add(request);
    }



    public void getStops(final IAction<Stop[]> callback, int routeId) {
        JsonObjectWithAuthRequest request = new JsonObjectWithAuthRequest(Request.Method.POST,
                Endpoints.FIND_STOPS_BY_ROUTEID,
                utilityHelper.getJsonRouteId(routeId),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        callback.OnCompleted(stopsParser.convertToArray(jsonObject));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.OnError(null);
            }
        });

        App.getsInstance().getmRequestQueue().add(request);
    }

    public void getTimes(final IAction<Time[]> callback, int routeId) {

        JsonObjectWithAuthRequest request = new JsonObjectWithAuthRequest(Request.Method.POST,
                Endpoints.FIND_DEPARTURES_BY_ROUTEID,
                utilityHelper.getJsonRouteId(routeId),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        callback.OnCompleted(timesParser.convertToArray(jsonObject));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.OnError(null);
            }
        });

        App.getsInstance().getmRequestQueue().add(request);
    }

}

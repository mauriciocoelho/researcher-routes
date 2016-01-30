package com.mauscoelho.researcherroutes.network.parsers;


import com.mauscoelho.researcherroutes.network.models.Time;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.Stop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RouteParser {

    @Inject
    public RouteParser() {
    }

    public List<Route> parseRoutes(JSONObject objRoute){

        List<Route> routes = new ArrayList<>();

        try {
            JSONArray dataArray = objRoute.getJSONArray("rows");

            for (int i = 0; i < dataArray.length(); i++) {

                if (dataArray.isNull(i))
                    continue;

                JSONObject dataObj = dataArray.getJSONObject(i);

                Route route = new Route();
                route.id = dataObj.getInt("id");
                route.shortName = dataObj.getString("shortName");
                route.longName = dataObj.getString("longName");
                route.lastModifiedDate = dataObj.getString("lastModifiedDate");
                route.agencyId = dataObj.getInt("agencyId");
                routes.add(route);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return routes;

    }

    public List<Stop> parseStops(JSONObject objRoute){

        List<Stop> stops = new ArrayList<>();

        try {
            JSONArray dataArray = objRoute.getJSONArray("rows");

            for (int i = 0; i < dataArray.length(); i++) {

                if (dataArray.isNull(i))
                    continue;

                JSONObject dataObj = dataArray.getJSONObject(i);

                Stop stop = new Stop();
                stop.id = dataObj.getInt("id");
                stop.name = dataObj.getString("name");
                stop.sequence = dataObj.getInt("sequence");
                stop.route_id = dataObj.getInt("route_id");
                stops.add(stop);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stops;

    }

    public List<Time> parseTimes(JSONObject objRoute){

        List<Time> time = new ArrayList<>();

        try {
            JSONArray dataArray = objRoute.getJSONArray("rows");

            for (int i = 0; i < dataArray.length(); i++) {

                if (dataArray.isNull(i))
                    continue;

                JSONObject dataObj = dataArray.getJSONObject(i);

                Time departureByRoute = new Time();
                departureByRoute.id = dataObj.getInt("id");
                departureByRoute.calendar = dataObj.getString("calendar");
                departureByRoute.time = dataObj.getString("time");
                time.add(departureByRoute);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return time;

    }




}

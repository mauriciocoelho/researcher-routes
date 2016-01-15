package com.mauscoelho.researcherroutes.network.parsers;


import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.StopsByRoute;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RouteParser {

    public List<Route> findRoutesByStopName(JSONObject objRoute){

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

    public List<StopsByRoute> findStopsByRouteId(JSONObject objRoute){

        List<StopsByRoute> stopsByRoutes = new ArrayList<>();

        try {
            JSONArray dataArray = objRoute.getJSONArray("rows");

            for (int i = 0; i < dataArray.length(); i++) {

                if (dataArray.isNull(i))
                    continue;

                JSONObject dataObj = dataArray.getJSONObject(i);

                StopsByRoute stopsByRoute = new StopsByRoute();
                stopsByRoute.id = dataObj.getInt("id");
                stopsByRoute.name = dataObj.getString("name");
                stopsByRoute.sequence = dataObj.getInt("sequence");
                stopsByRoute.route_id = dataObj.getInt("route_id");
                stopsByRoutes.add(stopsByRoute);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stopsByRoutes;

    }


}

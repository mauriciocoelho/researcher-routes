package com.mauscoelho.researcherroutes.network.services;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mauscoelho.researcherroutes.App;
import com.mauscoelho.researcherroutes.network.Endpoints;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.models.Routes;

import org.json.JSONException;
import org.json.JSONObject;

public class RouteService {

    public void findRoutesByStopName(final IAction<Routes> callback, String stopName) {
        JsonObjectRequest request = null;
        try {
            final JSONObject jsonBody = new JSONObject("");

            request = new JsonObjectRequest(Endpoints.FIND_ROUTES_BY_STOPNAME, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //TODO parse routes
                    callback.OnCompleted(new Routes());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.OnError(new Routes());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        App.getsInstance().getmRequestQueue().add(request);

    }

}

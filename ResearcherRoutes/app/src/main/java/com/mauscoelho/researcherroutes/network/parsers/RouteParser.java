package com.mauscoelho.researcherroutes.network.parsers;


import com.google.gson.Gson;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.util.UtilityHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;

public class RouteParser {

    UtilityHelper utilityHelper;
    Gson gson;

    @Inject
    public RouteParser(UtilityHelper utilityHelper, Gson gson) {
        this.utilityHelper = utilityHelper;
        this.gson = gson;
    }

    public Route[] convertToArray(JSONObject jsonObject){
        JSONArray jsonArray = utilityHelper.getJsonArray(jsonObject);
        Route[] routes = gson.fromJson(jsonArray.toString(), Route[].class);
        return routes;
    }
}

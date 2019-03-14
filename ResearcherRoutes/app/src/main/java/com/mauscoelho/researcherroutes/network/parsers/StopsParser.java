package com.mauscoelho.researcherroutes.network.parsers;

import com.google.gson.Gson;
import com.mauscoelho.researcherroutes.network.models.Stop;
import com.mauscoelho.researcherroutes.network.util.UtilityHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;

public class StopsParser {
    private UtilityHelper utilityHelper;
    private Gson gson;

    @Inject
    public StopsParser(UtilityHelper utilityHelper, Gson gson) {
        this.utilityHelper = utilityHelper;
        this.gson = gson;
    }

    public Stop[] convertToArray(JSONObject jsonObject){
        JSONArray jsonArray = utilityHelper.getJsonArray(jsonObject);
        Stop[] stops = gson.fromJson(jsonArray.toString(), Stop[].class);
        return stops;
    }
}

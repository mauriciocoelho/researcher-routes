package com.mauscoelho.researcherroutes.network.parsers;


import com.google.gson.Gson;
import com.mauscoelho.researcherroutes.network.models.Time;
import com.mauscoelho.researcherroutes.network.util.UtilityHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;

public class TimesParser {
    UtilityHelper utilityHelper;
    Gson gson;

    @Inject
    public TimesParser(UtilityHelper utilityHelper, Gson gson) {
        this.utilityHelper = utilityHelper;
        this.gson = gson;
    }

    public Time[] convertToArray(JSONObject jsonObject){
        JSONArray jsonArray = utilityHelper.getJsonArray(jsonObject);
        Time[] times = gson.fromJson(jsonArray.toString(), Time[].class);
        return times;
    }
}

package com.mauscoelho.researcherroutes.network.util;


import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class UtilityHelper {

    @Inject
    public UtilityHelper() {
    }

    @NonNull
    public JSONObject getJsonObjectByStopName(String value)  {
        if(value.isEmpty())
            throw new NullPointerException("parameter value is null");
        try {
            return new JSONObject("{\n" +
                    "\"params\": {\n" +
                    "\"stopName\": \"%" + value + "%\"\n" +
                    "}\n" +
                    "}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @NonNull
    public JSONObject getJsonRouteId(int value)  {
        try {
            return new JSONObject("{\n" +
                    "\"params\": {\n" +
                    "\"routeId\": "+ value +" \n" +
                    "}\n" +
                    "}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public JSONArray getJsonArray(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("rows");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

}

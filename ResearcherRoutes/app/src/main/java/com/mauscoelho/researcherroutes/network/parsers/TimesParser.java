package com.mauscoelho.researcherroutes.network.parsers;


import com.mauscoelho.researcherroutes.network.interfaces.IParserCommand;
import com.mauscoelho.researcherroutes.network.models.Time;

import org.json.JSONException;
import org.json.JSONObject;

public class TimesParser implements IParserCommand{

    @Override
    public Object executeParser(JSONObject dataObj) {
        Time time = new Time();
        try {
            time.id = dataObj.getInt("id");
            time.calendar = dataObj.getString("calendar");
            time.time = dataObj.getString("time");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return time;
    }
}

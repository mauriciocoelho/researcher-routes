package com.mauscoelho.researcherroutes.network.parsers;


import com.mauscoelho.researcherroutes.network.interfaces.IParserCommand;
import com.mauscoelho.researcherroutes.network.models.Stop;

import org.json.JSONException;
import org.json.JSONObject;

public class StopsParserCommand implements IParserCommand{

    @Override
    public Object executeParser(JSONObject dataObj) {
        Stop stop = new Stop();
        try {
            stop.id = dataObj.getInt("id");
            stop.name = dataObj.getString("name");
            stop.sequence = dataObj.getInt("sequence");
            stop.route_id = dataObj.getInt("route_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stop;
    }
}

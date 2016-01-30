package com.mauscoelho.researcherroutes.network.parsers;


import com.mauscoelho.researcherroutes.network.interfaces.IParserCommand;
import com.mauscoelho.researcherroutes.network.models.Route;

import org.json.JSONException;
import org.json.JSONObject;

public class RoutesParserCommand implements IParserCommand {
    @Override
    public Object executeParser(JSONObject dataObj) {
        Route route = new Route();
        try {
            route.id = dataObj.getInt("id");
            route.shortName = dataObj.getString("shortName");
            route.longName = dataObj.getString("longName");
            route.lastModifiedDate = dataObj.getString("lastModifiedDate");
            route.agencyId = dataObj.getInt("agencyId");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return route;
    }
}

package com.mauscoelho.researcherroutes.network.parsers;


import com.mauscoelho.researcherroutes.network.interfaces.IParserCommand;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.Stop;
import com.mauscoelho.researcherroutes.network.models.Time;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Parser {

    public static final String ROWS = "rows";
    private RoutesParserCommand routesParserCommand;
    private StopsParserCommand stopsParserCommand;
    private TimesParserCommand timesParserCommand;

    @Inject
    public Parser(RoutesParserCommand routesParserCommand, StopsParserCommand stopsParserCommand, TimesParserCommand timesParserCommand) {
        this.routesParserCommand = routesParserCommand;
        this.stopsParserCommand = stopsParserCommand;
        this.timesParserCommand = timesParserCommand;
    }

    private List<Object> execute(JSONObject objRoute, IParserCommand parserCommand) {
        List<Object> objectList = new ArrayList<>();
        try {
            JSONArray dataArray = objRoute.getJSONArray(ROWS);

            for (int i = 0; i < dataArray.length(); i++) {
                if (dataArray.isNull(i))
                    continue;
                JSONObject dataObj = dataArray.getJSONObject(i);
                Object item = parserCommand.executeParser(dataObj);
                objectList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    public List<Route> parseRoutes(JSONObject objRoute) {
        return (List<Route>) (Object) execute(objRoute, routesParserCommand);
    }

    public List<Stop> parseStops(JSONObject objRoute) {
        return (List<Stop>) (Object) execute(objRoute, stopsParserCommand);
    }

    public List<Time> parseTimes(JSONObject objRoute) {
        return (List<Time>) (Object) execute(objRoute, timesParserCommand);
    }

}

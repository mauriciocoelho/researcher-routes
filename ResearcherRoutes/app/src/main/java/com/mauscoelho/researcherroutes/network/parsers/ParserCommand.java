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

public class ParserCommand {

    RoutesParser routesParser;
    StopsParser stopsParser;
    TimesParser timesParser;

    @Inject
    public ParserCommand(RoutesParser routesParser, StopsParser stopsParser, TimesParser timesParser) {
        this.routesParser = routesParser;
        this.stopsParser = stopsParser;
        this.timesParser = timesParser;
    }

    private List<Object> execute(JSONObject objRoute, IParserCommand parserCommand) {
        List<Object> objectList = new ArrayList<>();
        try {
            JSONArray dataArray = objRoute.getJSONArray("rows");

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

    public List<Route> executeRoutes(JSONObject objRoute) {
        return (List<Route>) (Object) execute(objRoute, routesParser);
    }

    public List<Stop> executeStops(JSONObject objRoute) {
        return (List<Stop>) (Object) execute(objRoute, stopsParser);
    }

    public List<Time> executeTimes(JSONObject objRoute) {
        return (List<Time>) (Object) execute(objRoute, timesParser);
    }

}

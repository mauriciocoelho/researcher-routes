package com.mauscoelho.researcherroutes.network.interfaces;


import org.json.JSONObject;

public interface IParserCommand {
    Object executeParser(JSONObject dataObj);
}

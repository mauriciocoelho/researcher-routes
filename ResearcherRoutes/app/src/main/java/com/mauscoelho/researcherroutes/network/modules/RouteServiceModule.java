package com.mauscoelho.researcherroutes.network.modules;

import com.google.gson.Gson;
import com.mauscoelho.researcherroutes.network.UtilityHelper;
import com.mauscoelho.researcherroutes.network.services.RouteService;

import dagger.Module;
import dagger.Provides;

@Module
public class RouteServiceModule {


    @Provides
    RouteService provideRouteService(){
        return  new RouteService(new Gson(), new UtilityHelper());
    }

}

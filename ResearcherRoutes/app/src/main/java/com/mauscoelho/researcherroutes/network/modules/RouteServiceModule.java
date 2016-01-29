package com.mauscoelho.researcherroutes.network.modules;

import com.mauscoelho.researcherroutes.network.services.RouteService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RouteServiceModule {

    @Provides @Singleton
    RouteService provideRouteService(){
        return  new RouteService();
    }

}

package com.mauscoelho.researcherroutes.network.modules;

import com.google.gson.Gson;
import com.mauscoelho.researcherroutes.network.parsers.RouteParser;
import com.mauscoelho.researcherroutes.network.parsers.StopsParser;
import com.mauscoelho.researcherroutes.network.parsers.TimesParser;
import com.mauscoelho.researcherroutes.network.util.UtilityHelper;
import com.mauscoelho.researcherroutes.network.services.RouteService;

import dagger.Module;
import dagger.Provides;

@Module
public class RouteServiceModule {

    @Provides
    UtilityHelper provideUtilityHelper(){
        return new UtilityHelper();
    }

    @Provides
    Gson provideGson(){
        return new Gson();
    }

    @Provides
    RouteParser provideRouteParser(){
        return new RouteParser(provideUtilityHelper(), provideGson());
    }

    @Provides
    StopsParser provideStopsParser(){
        return new StopsParser(provideUtilityHelper(), provideGson());
    }

    @Provides
    TimesParser provideTimesParser(){
        return new TimesParser(provideUtilityHelper(), provideGson());
    }

    @Provides
    RouteService provideRouteService(){
        return new RouteService(provideUtilityHelper(), provideRouteParser(), provideStopsParser(), provideTimesParser());
    }

}

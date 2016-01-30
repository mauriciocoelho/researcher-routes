package com.mauscoelho.researcherroutes.network.modules;

import com.mauscoelho.researcherroutes.network.parsers.ParserCommand;
import com.mauscoelho.researcherroutes.network.parsers.RoutesParser;
import com.mauscoelho.researcherroutes.network.parsers.StopsParser;
import com.mauscoelho.researcherroutes.network.parsers.TimesParser;
import com.mauscoelho.researcherroutes.network.services.RouteService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RouteServiceModule {


    @Provides @Singleton
    ParserCommand provideParserCommand(){
        return new ParserCommand(new RoutesParser(),new StopsParser(), new TimesParser());
    }

    @Provides @Singleton
    RouteService provideRouteService(){
        return  new RouteService(provideParserCommand());
    }

}

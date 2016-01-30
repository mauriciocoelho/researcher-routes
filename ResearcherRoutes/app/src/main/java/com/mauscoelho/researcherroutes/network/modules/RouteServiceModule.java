package com.mauscoelho.researcherroutes.network.modules;

import com.mauscoelho.researcherroutes.network.parsers.Parser;
import com.mauscoelho.researcherroutes.network.parsers.RoutesParserCommand;
import com.mauscoelho.researcherroutes.network.parsers.StopsParserCommand;
import com.mauscoelho.researcherroutes.network.parsers.TimesParserCommand;
import com.mauscoelho.researcherroutes.network.services.RouteService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RouteServiceModule {


    @Provides
    Parser provideParserCommand(){
        return new Parser(new RoutesParserCommand(),new StopsParserCommand(), new TimesParserCommand());
    }

    @Provides
    RouteService provideRouteService(){
        return  new RouteService(provideParserCommand());
    }

}

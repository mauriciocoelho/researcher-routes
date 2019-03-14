package com.mauscoelho.researcherroutes.network.interfaces;


import android.support.v7.app.AppCompatActivity;

import com.mauscoelho.researcherroutes.network.modules.RouteServiceModule;
import com.mauscoelho.researcherroutes.network.services.RouteService;
import com.mauscoelho.researcherroutes.ui.activities.MainActivity;
import com.mauscoelho.researcherroutes.ui.activities.StopsActivity;
import com.mauscoelho.researcherroutes.ui.activities.TimesActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RouteServiceModule.class})
public interface IRouteServiceComponent {

    void injectMainActivity(MainActivity activity);
    void injectStopsActivity(StopsActivity activity);
    void injectTimesActivity(TimesActivity activity);

    RouteService provideRouteService();

}

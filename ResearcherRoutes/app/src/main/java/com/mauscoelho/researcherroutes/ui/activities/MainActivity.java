package com.mauscoelho.researcherroutes.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.services.RouteService;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    RouteService _routeService = new RouteService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        _routeService.findRoutesByStopName(new IAction<List<Route>>() {
            @Override
            public void OnCompleted(List<Route> routes) {

            }

            @Override
            public void OnError(List<Route> routes) {

            }
        }, "lauro linhares");
    }
}

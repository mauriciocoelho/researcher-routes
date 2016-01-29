package com.mauscoelho.researcherroutes.ui.activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.interfaces.DaggerIRouteServiceComponent;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.interfaces.IRouteServiceComponent;
import com.mauscoelho.researcherroutes.network.models.DeparturesByRoute;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.StopsByRoute;
import com.mauscoelho.researcherroutes.network.services.RouteService;
import com.mauscoelho.researcherroutes.ui.adapters.DeparturesByRouteAdapter;
import com.mauscoelho.researcherroutes.ui.adapters.StopsByRouteAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StopsActivity extends AppCompatActivity {

    @Inject
    RouteService _routeService;

    private Toolbar toolbar;
    private Route route;
    private RecyclerView rv_stops;
    private Activity activity = this;
    private ProgressBar loader_stops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);
        route = (Route) getIntent().getSerializableExtra("route");

        IRouteServiceComponent routeServiceComponent = DaggerIRouteServiceComponent.create();
        routeServiceComponent.injectStopsActivity(this);

        FindById();
        SetToolbar();
        findStopsByRouteId(route.id);
    }

    private void FindById() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        loader_stops = (ProgressBar)findViewById(R.id.loader_stops);

        rv_stops = (RecyclerView) findViewById(R.id.rv_stops);
        LinearLayoutManager linearLayoutManagerStops = new LinearLayoutManager(this);
        linearLayoutManagerStops.setOrientation(LinearLayoutManager.VERTICAL);
        rv_stops.setLayoutManager(linearLayoutManagerStops);


    }

    private void SetToolbar() {
        View view = toolbar.getRootView();
        ImageView toolbar_back = (ImageView) view.findViewById(R.id.toolbar_back);
        TextView toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);
        toolbar_title.setText(route.longName);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findStopsByRouteId(int routeId) {
        if (routeId != 0) {
            _routeService.findStopsByRouteId(new IAction<List<StopsByRoute>>() {
                @Override
                public void OnCompleted(List<StopsByRoute> stopsByRoutes) {
                    if (stopsByRoutes.size() > 0) {

                        StopsByRouteAdapter stopsByRouteAdapter = new StopsByRouteAdapter(stopsByRoutes, activity);
                        rv_stops.setAdapter(stopsByRouteAdapter);
                        loader_stops.setVisibility(View.GONE);
                        rv_stops.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void OnError(List<StopsByRoute> stopsByRoutes) {
                    loader_stops.setVisibility(View.GONE);
                }
            }, routeId);
        }
    }


}

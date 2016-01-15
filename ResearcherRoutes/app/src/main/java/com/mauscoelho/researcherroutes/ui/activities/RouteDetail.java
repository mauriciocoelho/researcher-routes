package com.mauscoelho.researcherroutes.ui.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.models.DeparturesByRoute;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.StopsByRoute;
import com.mauscoelho.researcherroutes.network.services.RouteService;

import java.util.List;

public class RouteDetail extends AppCompatActivity {

    private Toolbar toolbar;
    private Route route;
    private RouteService _routeService = new RouteService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);
        route = (Route) getIntent().getSerializableExtra("route");

        FindById();
        SetToolbar();
        findStopsByRouteId(route.id);
        findDeparturesByRouteId(route.id);
    }

    private void FindById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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


                }

                @Override
                public void OnError(List<StopsByRoute> stopsByRoutes) {

                }
            }, routeId);
        }
    }

    private void findDeparturesByRouteId(int routeId) {
        if (routeId != 0) {
            _routeService.findDeparturesByRouteId(new IAction<List<DeparturesByRoute>>() {
                @Override
                public void OnCompleted(List<DeparturesByRoute> departuresByRoute) {


                }

                @Override
                public void OnError(List<DeparturesByRoute> departuresByRoute) {

                }
            }, routeId);
        }
    }

}

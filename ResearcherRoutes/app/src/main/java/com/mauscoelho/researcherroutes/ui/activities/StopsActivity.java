package com.mauscoelho.researcherroutes.ui.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.Extras;
import com.mauscoelho.researcherroutes.network.interfaces.DaggerIRouteServiceComponent;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.interfaces.IRouteServiceComponent;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.Stop;
import com.mauscoelho.researcherroutes.network.services.RouteService;
import com.mauscoelho.researcherroutes.ui.adapters.StopsByRouteAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class StopsActivity extends AppCompatActivity {

    @Inject
    RouteService _routeService;
    @InjectView(R.id.rv_stops)
    RecyclerView rv_stops;
    @InjectView(R.id.loader_stops)
    ProgressBar loader_stops;
    @InjectView(R.id.toolbar_title)
    TextView toolbar_title;

    Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);
        route = (Route) getIntent().getSerializableExtra(Extras.ROUTE_OBJECT);
        injectDependencies();
        injectViews();
        setTitle();
        findStopsByRouteId(route.id);
    }

    @OnClick(R.id.toolbar_back)
    public void backActivity(ImageView toolbar_back) {
        finish();
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }

    private void injectDependencies() {
        IRouteServiceComponent routeServiceComponent = DaggerIRouteServiceComponent.create();
        routeServiceComponent.injectStopsActivity(this);
    }

    private void setTitle() {
        toolbar_title.setText(route.longName);
    }

    private void findStopsByRouteId(int routeId) {
        _routeService.findStopsByRouteId(new IAction<List<Stop>>() {
            @Override
            public void OnCompleted(List<Stop> stops) {
                setRecycler(stops);
            }

            @Override
            public void OnError(List<Stop> stops) {
                loader_stops.setVisibility(View.GONE);
            }
        }, routeId);
    }

    private void setRecycler(List<Stop> stops) {
        rv_stops.setAdapter(new StopsByRouteAdapter(stops));
        loader_stops.setVisibility(View.GONE);
        rv_stops.setVisibility(View.VISIBLE);
    }


}

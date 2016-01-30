package com.mauscoelho.researcherroutes.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.interfaces.DaggerIRouteServiceComponent;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.interfaces.IRouteServiceComponent;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.services.RouteService;
import com.mauscoelho.researcherroutes.ui.adapters.RouteAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Inject
    RouteService _routeService;
    @InjectView(R.id.loader)
    ProgressBar loader;
    @InjectView(R.id.rv_route)
    RecyclerView rv_route;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.toolbar_text)
    EditText toolbar_text;
    @InjectView(R.id.nothing_found)
    TextView nothing_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectDependencies();
        injectViews();
    }

    private void injectDependencies() {
        IRouteServiceComponent routeServiceComponent = DaggerIRouteServiceComponent.create();
        routeServiceComponent.injectMainActivity(this);
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }

    @OnClick(R.id.toolbar_search)
    public void executeSearch(ImageView toolbar_search) {
        searchRoute(toolbar_text.getText().toString());
    }

    private void searchRoute(String stopName) {
        nothing_found.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);

        _routeService.findRoutesByStopName(new IAction<List<Route>>() {
            @Override
            public void OnCompleted(List<Route> routes) {
                loader.setVisibility(View.GONE);
                setRecycler(routes);
            }

            @Override
            public void OnError(List<Route> routes) {
                loader.setVisibility(View.GONE);
                nothing_found.setVisibility(View.VISIBLE);
            }
        }, stopName);
    }

    private void setRecycler(List<Route> routes) {
        rv_route.setAdapter(new RouteAdapter(routes, this));
    }
}


package com.mauscoelho.researcherroutes.ui.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.services.RouteService;
import com.mauscoelho.researcherroutes.ui.adapters.RouteAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RouteService _routeService = new RouteService();
    private ProgressBar loader;
    private RecyclerView rv_route;
    private LinearLayoutManager _linearLayoutManager;
    private Activity activity = this;
    private RouteAdapter routeAdapter = null;
    private Toolbar toolbar;
    private EditText toolbar_text;
    private TextView nothing_found;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindById();
        SetToolbar();
    }

    private void FindById() {
        rv_route = (RecyclerView) findViewById(R.id.rv_route);
        loader = (ProgressBar) findViewById(R.id.loader);
        _linearLayoutManager = new LinearLayoutManager(this);
        _linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_route.setLayoutManager(_linearLayoutManager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        nothing_found = (TextView)findViewById(R.id.nothing_found);
    }

    private void SetToolbar() {
        View view = toolbar.getRootView();
        ImageView toolbar_search = (ImageView) view.findViewById(R.id.toolbar_search);
        toolbar_text = (EditText) view.findViewById(R.id.toolbar_text);
        toolbar_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchRoute(toolbar_text.getText().toString());
            }
        });

    }

    private void searchRoute(String stopName) {
        if (!stopName.isEmpty()) {
            nothing_found.setVisibility(View.GONE);
            loader.setVisibility(View.VISIBLE);
            _routeService.findRoutesByStopName(new IAction<List<Route>>() {
                @Override
                public void OnCompleted(List<Route> routes) {
                    loader.setVisibility(View.GONE);

                    if (routes.size() == 0 & routeAdapter != null) {
                        routeAdapter.clearData();
                        nothing_found.setVisibility(View.VISIBLE);
                    }
                    else {
                        nothing_found.setVisibility(View.GONE);
                        routeAdapter = new RouteAdapter(routes, activity);
                        rv_route.setAdapter(routeAdapter);
                    }
                }

                @Override
                public void OnError(List<Route> routes) {
                    loader.setVisibility(View.GONE);
                    nothing_found.setVisibility(View.GONE);
                }
            }, stopName);
        }
    }
}

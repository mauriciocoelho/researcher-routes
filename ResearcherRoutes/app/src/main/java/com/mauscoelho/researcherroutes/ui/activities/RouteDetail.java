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
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.models.DeparturesByRoute;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.StopsByRoute;
import com.mauscoelho.researcherroutes.network.services.RouteService;
import com.mauscoelho.researcherroutes.ui.adapters.DeparturesByRouteAdapter;

import java.util.ArrayList;
import java.util.List;

public class RouteDetail extends AppCompatActivity {

    private Toolbar toolbar;
    private Route route;
    private RouteService _routeService = new RouteService();
    private RecyclerView rv_weekday;
    private RecyclerView rv_saturday;
    private RecyclerView rv_sunday;
    private Activity activity = this;
    private ProgressBar loader_weekday;
    private ProgressBar loader_saturday;
    private ProgressBar loader_sunday;

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
        loader_weekday = (ProgressBar)findViewById(R.id.loader_weekday);
        loader_saturday = (ProgressBar)findViewById(R.id.loader_saturday);
        loader_sunday = (ProgressBar)findViewById(R.id.loader_sunday);

        rv_weekday = (RecyclerView) findViewById(R.id.rv_weekday);
        LinearLayoutManager linearLayoutManagerWeekDay = new LinearLayoutManager(this);
        linearLayoutManagerWeekDay.setOrientation(LinearLayoutManager.VERTICAL);
        rv_weekday.setLayoutManager(linearLayoutManagerWeekDay);

        rv_saturday = (RecyclerView) findViewById(R.id.rv_saturday);
        LinearLayoutManager linearLayoutManagerSaturday = new LinearLayoutManager(this);
        linearLayoutManagerSaturday.setOrientation(LinearLayoutManager.VERTICAL);
        rv_saturday.setLayoutManager(linearLayoutManagerSaturday);

        rv_sunday = (RecyclerView) findViewById(R.id.rv_sunday);
        LinearLayoutManager linearLayoutManagerSunday = new LinearLayoutManager(this);
        linearLayoutManagerSunday.setOrientation(LinearLayoutManager.VERTICAL);
        rv_sunday.setLayoutManager(linearLayoutManagerSunday);


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
                    if (departuresByRoute.size() > 0) {
                        DeparturesByRouteAdapter weekdayAdapter = new DeparturesByRouteAdapter(getWeekdaysList(departuresByRoute), activity);
                        rv_weekday.setAdapter(weekdayAdapter);
                        loader_weekday.setVisibility(View.GONE);
                        rv_weekday.setVisibility(View.VISIBLE);

                        DeparturesByRouteAdapter saturdayAdapter = new DeparturesByRouteAdapter(getSaturdaysList(departuresByRoute), activity);
                        rv_saturday.setAdapter(saturdayAdapter);
                        loader_saturday.setVisibility(View.GONE);
                        rv_saturday.setVisibility(View.VISIBLE);

                        DeparturesByRouteAdapter sundayAdapter = new DeparturesByRouteAdapter(getSundaysList(departuresByRoute), activity);
                        rv_sunday.setAdapter(sundayAdapter);
                        loader_sunday.setVisibility(View.GONE);
                        rv_sunday.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void OnError(List<DeparturesByRoute> departuresByRoute) {
                    loader_weekday.setVisibility(View.GONE);
                    loader_saturday.setVisibility(View.GONE);
                }
            }, routeId);
        }
    }


    private List<DeparturesByRoute> getWeekdaysList(List<DeparturesByRoute> departuresByRoute) {
        List<DeparturesByRoute> weekdayList = new ArrayList<>();
        for (DeparturesByRoute item : departuresByRoute) {
            if (item.calendar.contains("WEEKDAY"))
                weekdayList.add(item);
        }

        return weekdayList;
    }

    private List<DeparturesByRoute> getSaturdaysList(List<DeparturesByRoute> departuresByRoute) {
        List<DeparturesByRoute> saturdayList = new ArrayList<>();
        for (DeparturesByRoute item : departuresByRoute) {
            if (item.calendar.contains("SATURDAY"))
                saturdayList.add(item);
        }

        return saturdayList;
    }

    private List<DeparturesByRoute> getSundaysList(List<DeparturesByRoute> departuresByRoute) {
        List<DeparturesByRoute> sundaysList = new ArrayList<>();
        for (DeparturesByRoute item : departuresByRoute) {
            if (item.calendar.contains("SUNDAY"))
                sundaysList.add(item);
        }

        return sundaysList;
    }

}

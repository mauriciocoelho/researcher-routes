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

public class TimesActivity extends AppCompatActivity {

    @Inject
    RouteService _routeService;

    public static final String WEEKDAY = "WEEKDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";
    private Toolbar toolbar;
    private Route route;
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
        setContentView(R.layout.activity_times);
        route = (Route) getIntent().getSerializableExtra("route");

        IRouteServiceComponent routeServiceComponent = DaggerIRouteServiceComponent.create();
        routeServiceComponent.injectTimesActivity(this);

        FindById();
        SetToolbar();
        findDeparturesByRouteId(route.id);
    }

    private void FindById() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        loader_weekday = (ProgressBar)findViewById(R.id.loader_weekday);
        loader_saturday = (ProgressBar)findViewById(R.id.loader_saturday);
        loader_sunday = (ProgressBar)findViewById(R.id.loader_sunday);

        rv_weekday = (RecyclerView) findViewById(R.id.rv_weekday);
        rv_saturday = (RecyclerView) findViewById(R.id.rv_saturday);
        rv_sunday = (RecyclerView) findViewById(R.id.rv_sunday);
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


    private void findDeparturesByRouteId(int routeId) {
        if (routeId != 0) {
            _routeService.findDeparturesByRouteId(new IAction<List<DeparturesByRoute>>() {
                @Override
                public void OnCompleted(List<DeparturesByRoute> departuresByRoute) {
                    if (departuresByRoute.size() > 0) {
                        bindDepartures(departuresByRoute);
                    }
                }

                @Override
                public void OnError(List<DeparturesByRoute> departuresByRoute) {
                    loader_weekday.setVisibility(View.GONE);
                    loader_saturday.setVisibility(View.GONE);
                    loader_sunday.setVisibility(View.GONE);
                }
            }, routeId);
        }
    }

    private void bindDepartures(List<DeparturesByRoute> departuresByRoute) {
        DeparturesByRouteAdapter weekdayAdapter = new DeparturesByRouteAdapter(getWeekdaysList(departuresByRoute), activity);
        setRecyclers(rv_weekday, loader_weekday, weekdayAdapter);

        DeparturesByRouteAdapter saturdayAdapter = new DeparturesByRouteAdapter(getSaturdaysList(departuresByRoute), activity);
        setRecyclers(rv_saturday, loader_saturday, saturdayAdapter);

        DeparturesByRouteAdapter sundayAdapter = new DeparturesByRouteAdapter(getSundaysList(departuresByRoute), activity);
        setRecyclers(rv_sunday, loader_sunday, sundayAdapter);
    }

    private void setRecyclers(RecyclerView recycler, ProgressBar loader, DeparturesByRouteAdapter adapter) {
        recycler.setAdapter(adapter);
        loader.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
    }


    private List<DeparturesByRoute> getWeekdaysList(List<DeparturesByRoute> departuresByRoute) {
        List<DeparturesByRoute> weekdayList = new ArrayList<>();
        for (DeparturesByRoute item : departuresByRoute) {
            if (item.calendar.contains(WEEKDAY))
                weekdayList.add(item);
        }

        return weekdayList;
    }

    private List<DeparturesByRoute> getSaturdaysList(List<DeparturesByRoute> departuresByRoute) {
        List<DeparturesByRoute> saturdayList = new ArrayList<>();
        for (DeparturesByRoute item : departuresByRoute) {
            if (item.calendar.contains(SATURDAY))
                saturdayList.add(item);
        }

        return saturdayList;
    }

    private List<DeparturesByRoute> getSundaysList(List<DeparturesByRoute> departuresByRoute) {
        List<DeparturesByRoute> sundaysList = new ArrayList<>();
        for (DeparturesByRoute item : departuresByRoute) {
            if (item.calendar.contains(SUNDAY))
                sundaysList.add(item);
        }

        return sundaysList;
    }

}

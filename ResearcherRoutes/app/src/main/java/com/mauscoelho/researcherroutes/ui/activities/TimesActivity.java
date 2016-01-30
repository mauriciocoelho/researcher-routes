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
import com.mauscoelho.researcherroutes.network.Extras;
import com.mauscoelho.researcherroutes.network.interfaces.DaggerIRouteServiceComponent;
import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.interfaces.IRouteServiceComponent;
import com.mauscoelho.researcherroutes.network.models.DeparturesByRoute;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.StopsByRoute;
import com.mauscoelho.researcherroutes.network.services.RouteService;
import com.mauscoelho.researcherroutes.ui.adapters.DeparturesByRouteAdapter;
import com.mauscoelho.researcherroutes.ui.adapters.StopsByRouteAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TimesActivity extends AppCompatActivity {

    public static final String WEEKDAY = "WEEKDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";

    @Inject
    RouteService routeService;
    @InjectView(R.id.rv_weekday)
    RecyclerView rv_weekday;
    @InjectView(R.id.rv_saturday)
    RecyclerView rv_saturday;
    @InjectView(R.id.rv_sunday)
    RecyclerView rv_sunday;
    @InjectView(R.id.loader_weekday)
    ProgressBar loader_weekday;
    @InjectView(R.id.loader_saturday)
    ProgressBar loader_saturday;
    @InjectView(R.id.loader_sunday)
    ProgressBar loader_sunday;
    @InjectView(R.id.toolbar_title)
    TextView toolbar_title;

    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);
        route = (Route) getIntent().getSerializableExtra(Extras.ROUTE_OBJECT);
        injectDependencies();
        injectViews();
        setTitle();
        findDeparturesByRouteId(route.id);
    }

    private void injectDependencies() {
        IRouteServiceComponent routeServiceComponent = DaggerIRouteServiceComponent.create();
        routeServiceComponent.injectTimesActivity(this);
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }

    private void setTitle() {
        toolbar_title.setText(route.longName);
    }

    @OnClick(R.id.toolbar_back)
    public void backActivity(ImageView toolbar_back) {
        finish();
    }

    private void findDeparturesByRouteId(int routeId) {
        if (routeId != 0) {
            routeService.findDeparturesByRouteId(new IAction<List<DeparturesByRoute>>() {
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
        setRecyclers(rv_weekday, loader_weekday, new DeparturesByRouteAdapter(getList(departuresByRoute, WEEKDAY)));
        setRecyclers(rv_saturday, loader_saturday, new DeparturesByRouteAdapter(getList(departuresByRoute, SATURDAY)));
        setRecyclers(rv_sunday, loader_sunday, new DeparturesByRouteAdapter(getList(departuresByRoute, SUNDAY)));
    }

    private void setRecyclers(RecyclerView recycler, ProgressBar loader, DeparturesByRouteAdapter adapter) {
        recycler.setAdapter(adapter);
        loader.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
    }

    private List<DeparturesByRoute> getList(List<DeparturesByRoute> departuresByRoute, String contains) {
        List<DeparturesByRoute> departuresByRouteList = new ArrayList<>();
        for (DeparturesByRoute item : departuresByRoute) {
            if (item.calendar.contains(contains))
                departuresByRouteList.add(item);
        }

        return departuresByRouteList;
    }

}

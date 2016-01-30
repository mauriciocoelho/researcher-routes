package com.mauscoelho.researcherroutes.ui.adapters;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.Extras;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.ui.activities.StopsActivity;
import com.mauscoelho.researcherroutes.ui.activities.TimesActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RouteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Route> routes;
    private Activity _activity;


    public RouteAdapter(List<Route> routes, Activity activity) {
        this.routes = routes;
        this._activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Route route = routes.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                bindMatchItem((RouteViewHolder) viewHolder, route);
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.card_route, viewGroup, false);
            return new RouteViewHolder(view);
        }
        return null;
    }


    private void bindMatchItem(RouteViewHolder viewHolder, final Route route) {
        viewHolder.route_shortName.setText(route.shortName);
        viewHolder.route_longName.setText(route.longName);

        viewHolder.route_times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(route, TimesActivity.class);
            }
        });

        viewHolder.route_stops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(route, StopsActivity.class);
            }
        });

    }

    private void startNewActivity(Route route, Class<?> activityClass) {
        Intent intent = new Intent(_activity, activityClass);
        intent.putExtra(Extras.ROUTE_OBJECT, route);
        _activity.startActivity(intent);
    }


    public static class RouteViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.route_shortName)
        TextView route_shortName;
        @InjectView(R.id.route_longName)
        TextView route_longName;
        @InjectView(R.id.route_times)
        TextView route_times;
        @InjectView(R.id.route_stops)
        TextView route_stops;


        public RouteViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
        }
    }
}


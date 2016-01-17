package com.mauscoelho.researcherroutes.ui.adapters;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.ui.activities.StopsActivity;
import com.mauscoelho.researcherroutes.ui.activities.TimesActivity;

import java.io.Serializable;
import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Route> _routes;
    private Activity _activity;


    public RouteAdapter(List<Route> routes, Activity activity) {
        this._routes = routes;
        this._activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return _routes.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Route route = _routes.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                bindMatchItem((RouteViewHolder) viewHolder, route);
                break;
        }
    }

    public void clearData() {
        int size = _routes.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                _routes.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
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
                Intent intent = new Intent(_activity, TimesActivity.class);
                intent.putExtra("route", (Serializable) route);
                _activity.startActivity(intent);
            }
        });

        viewHolder.route_stops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_activity ,StopsActivity.class);
                intent.putExtra("route", (Serializable)route);
                _activity.startActivity(intent);
            }
        });

    }


    public static class RouteViewHolder extends RecyclerView.ViewHolder {
        protected TextView route_shortName;
        protected TextView route_longName;
        protected TextView route_times;
        protected TextView route_stops;


        public RouteViewHolder(View v) {
            super(v);

            route_shortName = (TextView)v.findViewById(R.id.route_shortName);
            route_longName = (TextView)v.findViewById(R.id.route_longName);
            route_times = (TextView)v.findViewById(R.id.route_times);
            route_stops = (TextView)v.findViewById(R.id.route_stops);

        }
    }
}


package com.mauscoelho.researcherroutes.ui.adapters;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.models.Route;

import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Route> _matches;
    private Activity _fragment;

    public RouteAdapter(List<Route> matches, Activity fragment) {
        this._matches = matches;
        this._fragment = fragment;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return _matches.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Route match = _matches.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                bindMatchItem((RouteViewHolder) viewHolder, match);
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
        RouteViewHolder routeViewHolder = viewHolder;
        viewHolder.route_shortName.setText(route.shortName);

    }


    public static class RouteViewHolder extends RecyclerView.ViewHolder {
        protected TextView route_shortName;

        public RouteViewHolder(View v) {
            super(v);

            route_shortName = (TextView)v.findViewById(R.id.route_shortName);

        }
    }
}


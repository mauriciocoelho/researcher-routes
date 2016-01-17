package com.mauscoelho.researcherroutes.ui.adapters;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.models.DeparturesByRoute;
import com.mauscoelho.researcherroutes.network.models.Route;
import com.mauscoelho.researcherroutes.network.models.StopsByRoute;

import java.util.List;

public class DeparturesByRouteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DeparturesByRoute> _departuresByRoute;
    private Activity _activity;


    public DeparturesByRouteAdapter(List<DeparturesByRoute> departuresByRoutes, Activity activity) {
        this._departuresByRoute = departuresByRoutes;
        this._activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return _departuresByRoute.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        DeparturesByRoute departuresByRoute = _departuresByRoute.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                bindMatchItem((RouteViewHolder) viewHolder, departuresByRoute);
                break;
        }
    }

    public void clearData() {
        int size = _departuresByRoute.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                _departuresByRoute.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == 0) {
            View view = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.card_departures_item, viewGroup, false);


            return new RouteViewHolder(view);
        }

        return null;
    }


    private void bindMatchItem(RouteViewHolder viewHolder, final DeparturesByRoute departuresByRoute) {
        viewHolder.card_departures_item_text.setText(departuresByRoute.time);
    }


    public static class RouteViewHolder extends RecyclerView.ViewHolder {

        protected TextView card_departures_item_text;

        public RouteViewHolder(View v) {
            super(v);

            card_departures_item_text = (TextView)v.findViewById(R.id.card_departures_item_text);

        }
    }
}


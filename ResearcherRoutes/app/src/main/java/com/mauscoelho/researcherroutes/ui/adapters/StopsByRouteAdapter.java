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
import com.mauscoelho.researcherroutes.network.models.StopsByRoute;
import com.mauscoelho.researcherroutes.ui.activities.RouteDetail;

import java.io.Serializable;
import java.util.List;

public class StopsByRouteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<StopsByRoute> _stopsByRoute;
    private Activity _activity;


    public StopsByRouteAdapter(List<Route> stopsByRoute, Activity activity) {
        this._stopsByRoute = _stopsByRoute;
        this._activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return _stopsByRoute.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        StopsByRoute stopsByRoute = _stopsByRoute.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                bindMatchItem((RouteViewHolder) viewHolder, stopsByRoute);
                break;
        }
    }

    public void clearData() {
        int size = _stopsByRoute.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                _stopsByRoute.remove(0);
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


    private void bindMatchItem(RouteViewHolder viewHolder, final StopsByRoute stopsByRoute) {


    }


    public static class RouteViewHolder extends RecyclerView.ViewHolder {


        public RouteViewHolder(View v) {
            super(v);


        }
    }
}


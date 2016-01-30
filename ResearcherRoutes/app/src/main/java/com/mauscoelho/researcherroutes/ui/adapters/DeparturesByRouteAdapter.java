package com.mauscoelho.researcherroutes.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.models.DeparturesByRoute;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DeparturesByRouteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DeparturesByRoute> _departuresByRoute;

    public DeparturesByRouteAdapter(List<DeparturesByRoute> departuresByRoutes) {
        this._departuresByRoute = departuresByRoutes;
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_departures_item, viewGroup, false);
        return new RouteViewHolder(view);
    }


    private void bindMatchItem(RouteViewHolder viewHolder, final DeparturesByRoute departuresByRoute) {
        viewHolder.card_departures_item_text.setText(departuresByRoute.time);
    }


    public static class RouteViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.card_departures_item_text)
        TextView card_departures_item_text;

        public RouteViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
        }
    }
}


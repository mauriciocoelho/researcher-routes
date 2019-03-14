package com.mauscoelho.researcherroutes.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.models.Time;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DeparturesByRouteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Time> _time;

    public DeparturesByRouteAdapter(List<Time> times) {
        this._time = times;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return _time.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Time time = _time.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                bindMatchItem((RouteViewHolder) viewHolder, time);
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


    private void bindMatchItem(RouteViewHolder viewHolder, final Time time) {
        viewHolder.card_departures_item_text.setText(time.time);
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


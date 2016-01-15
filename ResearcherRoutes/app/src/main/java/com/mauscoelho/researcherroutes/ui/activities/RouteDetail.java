package com.mauscoelho.researcherroutes.ui.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mauscoelho.researcherroutes.R;
import com.mauscoelho.researcherroutes.network.models.Route;

public class RouteDetail extends AppCompatActivity {

    private Toolbar toolbar;
    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);
        route = (Route)getIntent().getSerializableExtra("route");

        FindById();
        SetToolbar();
    }

    private void FindById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
}

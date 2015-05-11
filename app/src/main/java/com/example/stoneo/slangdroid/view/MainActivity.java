package com.example.stoneo.slangdroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.stoneo.slangdroid.R;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openFlowList(View view) {
        Intent flowListIntent = new Intent(this, FlowListActivity.class);
        startActivity(flowListIntent);
    }
}

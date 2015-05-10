package com.example.stoneo.slangdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.stoneo.slangdroid.model.Flow;

import java.util.ArrayList;


public class Main extends ActionBarActivity {


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

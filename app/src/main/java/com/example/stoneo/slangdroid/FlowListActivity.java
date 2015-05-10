package com.example.stoneo.slangdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.stoneo.slangdroid.model.Flow;

import java.util.ArrayList;


public class FlowListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Flow> flows;
    private ArrayAdapter<Flow> flowsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_list);
        initFlowList();
        initList();
    }

    private void initFlowList() {
        flows = new ArrayList<>();
        flows.add(new Flow("flow1", "id1"));
        flows.add(new Flow("flow2", "id2"));
        flows.add(new Flow("flow3", "id3"));
    }

    private void initList() {
        ListView flowsList = (ListView) findViewById(R.id.flowList);
        flowsAdapter = new FlowListAdapter(this, flows);
        flowsList.setAdapter(flowsAdapter);
        flowsList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Flow flow = flowsAdapter.getItem(position);
        Toast toast = Toast.makeText(this, "Pos: " + position + " id: " + flow.getId(), Toast.LENGTH_SHORT);
        toast.show();
    }
}

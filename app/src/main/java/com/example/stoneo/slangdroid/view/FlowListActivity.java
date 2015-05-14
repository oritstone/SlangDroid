package com.example.stoneo.slangdroid.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.stoneo.slangdroid.view.adapters.FlowListAdapter;
import com.example.stoneo.slangdroid.R;
import com.example.stoneo.slangdroid.model.Flow;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FlowListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ArrayAdapter<Flow> flowsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_list);
        initFlowList();
    }

    private void initFlowList() {
        new FlowListTask().execute();
    }

    private void initList(List<Flow> flows) {
        ListView flowsList = (ListView) findViewById(R.id.flowList);
        flowsAdapter = new FlowListAdapter(this, flows);
        flowsList.setAdapter(flowsAdapter);
        flowsList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Flow flow = flowsAdapter.getItem(position);
        Intent flowLauncherIntent = new Intent(this, FlowLauncherActivity.class);
        flowLauncherIntent.putExtra("flowId", flow.getId());
        flowLauncherIntent.putExtra("flowName", flow.getName());
        flowLauncherIntent.putExtra("flowPath", flow.getPath());
        startActivity(flowLauncherIntent);
    }

    class FlowListTask extends AsyncTask<Void, Void, List<Flow>>{

        @Override
        protected List<Flow> doInBackground(Void... params) {

            List<Flow> flowsList = new ArrayList<>();
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet(getString(R.string.baseUrl) + "/flows");
            StringBuilder sb;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                sb = new StringBuilder();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()), 65728);
                    String line;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    JSONArray flows = new JSONArray(sb.toString());
                    int numOfFlows = flows.length();
                    for (int i = 0; i < numOfFlows; i++) {
                        JSONObject flow = flows.getJSONObject(i);
                        flowsList.add(new Flow(flow.getString("name"), flow.getString("id"), flow.getString("path")));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return flowsList;
        }

            @Override
        protected void onPostExecute(List<Flow> flows){
            initList(flows);
        }
    }
}

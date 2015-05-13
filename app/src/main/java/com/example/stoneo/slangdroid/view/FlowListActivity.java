package com.example.stoneo.slangdroid.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        Toast toast = Toast.makeText(this, "Pos: " + position + " id: " + flow.getId(), Toast.LENGTH_SHORT);
        toast.show();
        Intent flowLauncherIntent = new Intent(this, FlowLauncherActivity.class);
        flowLauncherIntent.putExtra("flowId", flow.getId());
        flowLauncherIntent.putExtra("flowName", flow.getName());
        startActivity(flowLauncherIntent);
    }

    class FlowListTask extends AsyncTask<Void, Void, List<Flow>>{

        //TODO - get from server
        @Override
        protected List<Flow> doInBackground(Void... params) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet("http://localhost:8080/flows");
            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();


            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList<Flow> flows = new ArrayList<>();
            flows.add(new Flow("flow1", "id1"));
            flows.add(new Flow("flow2", "id2"));
            flows.add(new Flow("flow3", "id3"));
            flows.add(new Flow("flow4", "id4"));
            flows.add(new Flow("flow5", "id5"));
            return flows;
        }

        @Override
        protected void onPostExecute(List<Flow> flows){
            initList(flows);
        }
    }
}

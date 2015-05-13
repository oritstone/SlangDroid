package com.example.stoneo.slangdroid.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stoneo.slangdroid.R;
import com.example.stoneo.slangdroid.model.FormFlowInput;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.util.ArrayList;
import java.util.List;

public class FlowLauncherActivity extends ActionBarActivity {

    private String flowId;
    private String flowName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        flowName = intent.getStringExtra("flowName");
        flowId = intent.getStringExtra("flowId");
        setContentView(R.layout.activity_flow_launcher);
        initUi();
        getData();
    }

    private void getData() {
        new GetFlowDataTask().execute(flowId);
    }


    private void initUi(){
        TextView flowNameLabel = (TextView)findViewById(R.id.flowLauncherFlowNameLabel);
        flowNameLabel.setText(flowName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flow_launcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void setInputs(List<FormFlowInput> flowInputs){
        ViewGroup inputsContainer = (ViewGroup) findViewById(R.id.flowLauncherInputsContainer);
        for(FormFlowInput flowInput : flowInputs) {
            View row = createInputRow(flowInput, inputsContainer);
            inputsContainer.addView(row, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private View createInputRow(FormFlowInput flowInput, ViewGroup inputsContainer) {
        View row = LayoutInflater.from(this).inflate(R.layout.flow_input_row, inputsContainer, false);
        TextView inputName = (TextView) row.findViewById(R.id.flowInputNameLabel);
        EditText inputValue = (EditText) row.findViewById(R.id.flowInputValueField);
        inputName.setText(flowInput.getName());
        inputValue.setText(flowInput.getDefaultValue());
        return row;
    }

    public void onClickCancel(View view) {
    }

    public void onClickLaunch(View view) {
        launchFlow();
    }

    private void startRunTracking(String runId) {
        Intent trackingIntent = new Intent(this, RunTrackingActivity.class);
        trackingIntent.putExtra("runId", runId);
        startActivity(trackingIntent);
    }

    private void launchFlow(){
        new LaunchFlowTask().execute(flowId);
    }

    class GetFlowDataTask extends AsyncTask<String, Void, List<FormFlowInput>> {

        //TODO - get from server
        @Override
        protected List<FormFlowInput> doInBackground(String... params) {
            String flowId = params[0];
            return getFormFlowInputs(flowId);
        }

        private List<FormFlowInput> getFormFlowInputs(String flowId) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet("http://localhost:8080/flow/" + flowId);
            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();


            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList<FormFlowInput> flowInputs1 = new ArrayList<>();
            flowInputs1.add(new FormFlowInput("input1", "value1", true));
            flowInputs1.add(new FormFlowInput("input2", "value2", false));
            flowInputs1.add(new FormFlowInput("input3", "", true));

            ArrayList<FormFlowInput> flowInputs2 = new ArrayList<>();
            flowInputs2.add(new FormFlowInput("input4", "", true));
            flowInputs2.add(new FormFlowInput("input5", "", false));
            flowInputs2.add(new FormFlowInput("input6", "value3", true));
            flowInputs2.add(new FormFlowInput("input7", "5", true));

            if(flowId.equals("id1"))
                return flowInputs1;
            else
                return flowInputs2;
        }

        @Override
        protected void onPostExecute(List<FormFlowInput> flowInputs){
            setInputs(flowInputs);
        }
    }

    class LaunchFlowTask extends AsyncTask<String, Void, String> {

        //TODO - get from server
        @Override
        protected String doInBackground(String... params) {
            String flowId = params[0];
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost("http://localhost:8080/executions" + flowId);
            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpPost, localContext);
                HttpEntity entity = response.getEntity();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return "77";
        }


        @Override
        protected void onPostExecute(String runId){
            startRunTracking(runId);
        }
    }

}

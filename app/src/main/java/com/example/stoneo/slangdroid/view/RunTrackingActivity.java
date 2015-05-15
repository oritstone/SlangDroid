package com.example.stoneo.slangdroid.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.stoneo.slangdroid.R;
import com.example.stoneo.slangdroid.model.RunSummary;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunTrackingActivity extends ActionBarActivity {

    private Long runId;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        runId = intent.getLongExtra("runId", -1);
        setContentView(R.layout.activity_run_tracking);
        progressBar = (ProgressBar) findViewById(R.id.flow_data_progress_bar);
        getData();
    }

    private void getData() {
        new GetRunStatusTask().execute(runId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_run_tracking, menu);
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

    class GetRunStatusTask extends AsyncTask<Long, Void, RunSummary> {

        @Override
        protected RunSummary doInBackground(Long... params) {
            RunSummary runSummary = null;
            String runStatus = "RUNNING";
            Long runId = params[0];

            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            String url = getString(R.string.baseUrl) + "/executions/" + runId;

            HttpGet httpGet = new HttpGet(url);
            StringBuilder sb;
            try {
                while(runStatus.equals("RUNNING")) {
                    HttpResponse response = httpClient.execute(httpGet, localContext);
                    HttpEntity entity = response.getEntity();
                    sb = new StringBuilder();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()), 65728);
                        String line;

                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        JSONObject result = new JSONObject(sb.toString());
                        runSummary = new RunSummary(result.getLong("executionId"), result.getString("status"), result.getString("result"), result.getString("outputs"));
                        runStatus = runSummary.getStatus();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return runSummary;
        }

        @Override
        protected void onPostExecute(RunSummary runSummary){
            progressBar.setVisibility(View.INVISIBLE);
            findViewById(R.id.run_status_label).setVisibility(View.VISIBLE);
            findViewById(R.id.run_result_label).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.run_status_value)).setText(runSummary.getStatus());
            ((TextView)findViewById(R.id.run_result_value)).setText(runSummary.getResult());
        }
    }
}

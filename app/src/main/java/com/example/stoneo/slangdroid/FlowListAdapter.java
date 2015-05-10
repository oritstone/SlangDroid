package com.example.stoneo.slangdroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.stoneo.slangdroid.model.Flow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by stoneo on 5/10/2015.
 */
public class FlowListAdapter extends ArrayAdapter<Flow> {

    public FlowListAdapter(Context context, List<Flow> flows) {
        super(context, -1, flows);
    }

    @SuppressLint("ViewHolder") @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Flow flow = getItem(position);

        View row = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView flowLabel = (TextView) row.findViewById(android.R.id.text1);

        flowLabel.setText(flow.getName());

        return row;
    }
}

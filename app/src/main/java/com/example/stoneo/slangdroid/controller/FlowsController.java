package com.example.stoneo.slangdroid.controller;

import com.example.stoneo.slangdroid.model.Flow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stoneo on 5/11/2015.
 */
public class FlowsController {

    public List<Flow> getAllAvailableFlows(){
        List<Flow> flows = new ArrayList<>();
        flows.add(new Flow("flow1", "id1"));
        flows.add(new Flow("flow2", "id2"));
        flows.add(new Flow("flow3", "id3"));
        return flows;
    }
}

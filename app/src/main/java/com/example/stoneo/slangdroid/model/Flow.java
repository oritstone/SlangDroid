package com.example.stoneo.slangdroid.model;

/**
 * Created by stoneo on 5/10/2015.
 */
public class Flow {

    private String name;

    private String id;

    public Flow(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

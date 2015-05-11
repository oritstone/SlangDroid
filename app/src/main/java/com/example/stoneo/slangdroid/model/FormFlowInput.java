package com.example.stoneo.slangdroid.model;

import java.io.Serializable;

/**
 * Created by stoneo on 5/11/2015.
 */
public class FormFlowInput {

    private String name;

    private String defaultValue;

    private Boolean isRequired;

    public FormFlowInput(String name, String defaultValue, Boolean isRequired) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.isRequired = isRequired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean isRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }
}

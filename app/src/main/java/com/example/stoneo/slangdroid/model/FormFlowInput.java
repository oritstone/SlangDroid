package com.example.stoneo.slangdroid.model;

/**
 * Created by stoneo on 5/11/2015.
 */
public class FormFlowInput {

    private String name;

    private String defaultValue;

    private Boolean required;

    public FormFlowInput(String name, String defaultValue, Boolean required) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.required = required;
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
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}

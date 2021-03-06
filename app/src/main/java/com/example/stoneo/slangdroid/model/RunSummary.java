package com.example.stoneo.slangdroid.model;

/**
 * Created by stoneo on 5/14/2015.
 */
public class RunSummary {
    private final Long executionId;
    private final String status;
    private final String result;
    private final String outputs;

    public RunSummary(Long executionId, String status, String result, String outputs) {
        this.executionId = executionId;
        this.status = status;
        this.result = result;
        this.outputs = outputs;
    }

    public Long getExecutionId() {
        return executionId;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public String getOutputs() {
        return outputs;
    }
}

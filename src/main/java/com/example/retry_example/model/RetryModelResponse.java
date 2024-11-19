package com.example.retry_example.model;

public class RetryModelResponse {

  private final String output;
  private final int statusCode;

  public RetryModelResponse(String output, int statusCode) {
    this.output = output;
    this.statusCode = statusCode;
  }

  public String getOutput() {
    return output;
  }

  public int getStatusCode() {
    return statusCode;
  }
}

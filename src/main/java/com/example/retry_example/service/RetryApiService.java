package com.example.retry_example.service;

import com.example.retry_example.client.RetryApiClient;
import com.example.retry_example.exception.DummyException;
import com.example.retry_example.model.RetryModelResponse;
import org.springframework.stereotype.Service;

@Service
public class RetryApiService {

  private final RetryApiClient retryApiClient;

  public RetryApiService(RetryApiClient retryApiClient) {
    this.retryApiClient = retryApiClient;
  }

  public RetryModelResponse sayHello(String input) {

    RetryModelResponse response = null;
    try {
      String output = retryApiClient.retry(input);
      response = new RetryModelResponse(output, 200);
    } catch (DummyException dummyException) {
      if (dummyException.getCode() == 500) {
        response = new RetryModelResponse("Internal server error", dummyException.getCode());
      }
    }
    return response;
  }
}

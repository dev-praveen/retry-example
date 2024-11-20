package com.example.retry_example.client;

import com.example.retry_example.exception.DummyException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@EnableRetry
public class RetryApiClient {

  @Retryable(
      retryFor = IllegalArgumentException.class,
      notRecoverable = DummyException.class,
      maxAttempts = 2,
      backoff = @Backoff(delay = 10000, multiplier = 2))
  public String retry(String input) throws DummyException {

    if ("".equals(input)) {
      throw new IllegalArgumentException("input is empty");
    } else if (Objects.isNull(input)) {
      throw new DummyException("input cannot be null", 400);
    }
    return "Returning same response: " + input;
  }

  @Recover
  private String recover(IllegalArgumentException e, String input) {

    var context = RetrySynchronizationManager.getContext();
    int retryCount = context != null ? context.getRetryCount() : 0;
    System.out.println("Recovering from OriginalException after " + retryCount + " attempts");
    throw new DummyException("OriginalException retries exhausted", 500);
  }
}

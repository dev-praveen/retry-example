package com.example.retry_example.exception;

public class DummyException extends RuntimeException {

  private final int code;

  public DummyException(String message, int code) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}

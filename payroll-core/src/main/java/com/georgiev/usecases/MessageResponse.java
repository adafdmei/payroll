package com.georgiev.usecases;

import java.util.HashMap;
import java.util.Map;

public class MessageResponse implements Response {

  private final String message;

  public MessageResponse(String message) {
    this.message = message;
  }

  @Override
  public String getAsString() {
    return message;
  }

  @Override
  public Map<String, Object> getAsMap() {
    Map<String, Object> map = new HashMap<>();
    map.put(message, message);
    return map;

  }
}

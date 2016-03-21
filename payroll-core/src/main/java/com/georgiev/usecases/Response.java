package com.georgiev.usecases;

import java.util.Map;

public interface Response {

  String getAsString();

  Map<String, Object> getAsMap();
}

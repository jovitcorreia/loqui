package com.castanhocorreia.loqui.domain;

import java.io.Serial;
import java.io.Serializable;

public record UserRecord(String username) implements Serializable {
  @Serial
  private static final long serialVersionUID = 1825575941745250949L;

  @Override
  public String toString() {
    return username;
  }
}

package com.castanhocorreia.loqui.domain;

import lombok.Data;

@Data
public class Message {
  private String content;
  private String sender;
  private String timestamp;
}

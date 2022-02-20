package com.castanhocorreia.loqui.domain;

import lombok.Data;

@Data
public class MessageModel {
  private String content;
  private String sender;
  private String timestamp;
}

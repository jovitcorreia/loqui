package com.castanhocorreia.loqui.controller;

import com.castanhocorreia.loqui.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@RestController
public class ChatController {
  public final KafkaTemplate<String, Message> template;

  @PostMapping()
  public void send(@RequestBody Message message) {
    message.setTimestamp(LocalDateTime.now().toString());
    try {
      template.send("chat", message).get();
    } catch (InterruptedException | ExecutionException exception) {
      exception.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }
}

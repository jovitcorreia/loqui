package com.castanhocorreia.loqui.controller;

import com.castanhocorreia.loqui.domain.MessageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@RestController
public class ChatController {
  public final KafkaTemplate<String, MessageModel> template;

  @MessageMapping("/send")
  @SendTo("/topic/group")
  public MessageModel broadcast(@Payload MessageModel messageModel) {
    return messageModel;
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public void send(@RequestBody MessageModel messageModel) {
    messageModel.setTimestamp(LocalDateTime.now().toString());
    try {
      template.send("chat", messageModel).get();
    } catch (InterruptedException | ExecutionException exception) {
      exception.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }
}

package com.castanhocorreia.loqui.controller;

import com.castanhocorreia.loqui.data.MessageData;
import com.castanhocorreia.loqui.domain.MessageModel;
import com.castanhocorreia.loqui.domain.UserRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@RestController
public class ChatController {
  public final KafkaTemplate<String, MessageModel> template;

  @PostMapping()
  public void send(@RequestBody MessageData messageData) {
    MessageModel messageModel =
        MessageModel.builder()
            .content(messageData.getContent())
            .sender(new UserRecord(messageData.getSender()))
            .dateTime(LocalDateTime.now(ZoneId.of("UTC")))
            .build();
    try {
        template.send("chat", messageModel).get();
    } catch (InterruptedException | ExecutionException exception) {
        exception.printStackTrace();
        Thread.currentThread().interrupt();
    }
  }
}

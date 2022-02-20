package com.castanhocorreia.loqui.event;

import com.castanhocorreia.loqui.domain.MessageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {
  private final SimpMessagingTemplate template;

  @KafkaListener(
      containerFactory = "getContainerFactory",
      topics = {"chat"},
      groupId = "0")
  public void listen(MessageModel messageModel) {
    template.convertAndSend("/topic/group", messageModel);
  }
}

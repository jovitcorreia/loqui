package com.castanhocorreia.loqui.event;

import com.castanhocorreia.loqui.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {
  private final SimpMessagingTemplate template;

  @KafkaListener(topics = "chat", groupId = "chat")
  public void listen(Message message) {
    template.convertAndSend("/topic/group", message);
  }
}

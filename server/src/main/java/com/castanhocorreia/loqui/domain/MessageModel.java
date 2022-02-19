package com.castanhocorreia.loqui.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MessageModel {
    private String content;
    private LocalDateTime dateTime;
    private UserRecord sender;
}

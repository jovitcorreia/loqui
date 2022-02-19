package com.castanhocorreia.loqui.config;

import com.castanhocorreia.loqui.domain.MessageModel;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ProducerConfig {
  @Bean
  public Map<String, Object> config() {
    var configs = new HashMap<String, Object>();
    configs.put(
        org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        "localhost:9092");
    configs.put(
        org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
    configs.put(
        org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        JsonSerializer.class);
    return configs;
  }

  @Bean
  public ProducerFactory<String, MessageModel> factory() {
    return new DefaultKafkaProducerFactory<>(config());
  }

  @Bean
  public KafkaTemplate<String, MessageModel> template() {
    return new KafkaTemplate<>(factory());
  }
}

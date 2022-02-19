package com.castanhocorreia.loqui.config;

import com.castanhocorreia.loqui.domain.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ListenerConfig {
  @Bean
  public Map<String, Object> config() {
    var configs = new HashMap<String, Object>();
    configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configs.put(ConsumerConfig.GROUP_ID_CONFIG, "chat");
    configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    return configs;
  }

  @Bean
  ConcurrentKafkaListenerContainerFactory<String, Message> factory() {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, Message>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @Bean
  public ConsumerFactory<String, Message> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        config(), new StringDeserializer(), new JsonDeserializer<>(Message.class));
  }
}

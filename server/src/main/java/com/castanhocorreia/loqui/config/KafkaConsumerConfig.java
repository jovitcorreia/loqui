package com.castanhocorreia.loqui.config;

import com.castanhocorreia.loqui.domain.MessageModel;
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
public class KafkaConsumerConfig {
  @Bean
  public Map<String, Object> getConsumerConfigs() {
    var configs = new HashMap<String, Object>();
    configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
    configs.put(ConsumerConfig.GROUP_ID_CONFIG, "0");
    configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    return configs;
  }

  @Bean
  ConcurrentKafkaListenerContainerFactory<String, MessageModel> getContainerFactory() {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, MessageModel>();
    factory.setConsumerFactory(getConsumerFactory());
    return factory;
  }

  @Bean
  public ConsumerFactory<String, MessageModel> getConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        getConsumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(MessageModel.class));
  }
}

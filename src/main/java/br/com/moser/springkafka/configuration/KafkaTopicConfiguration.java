package br.com.moser.springkafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Juliano Moser
 */
@Configuration
public class KafkaTopicConfiguration {

    private String topicName;

    public KafkaTopicConfiguration(@Value("${topic.name}") String topicName) {
        this.topicName = topicName;
    }

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topicName, 1, (short) 1);
    }
}

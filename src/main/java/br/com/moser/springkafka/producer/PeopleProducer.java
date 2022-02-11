package br.com.moser.springkafka.producer;

import br.com.moser.springkafka.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Slf4j
@Component
public class PeopleProducer {

    private final String topicName;
    private final KafkaTemplate<String, People>  kafkaTemplate;

    public PeopleProducer(@Value("${topic.name}") String topicName, KafkaTemplate<String, People> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(People people) {
        kafkaTemplate.send(topicName, people).addCallback(
                success -> log.info("Mensagem Enviado com sucesso!"),
                failure -> log.error("Falha ao Enviar Mensagem!")
        );
    }
}

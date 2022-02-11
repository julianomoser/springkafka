package br.com.moser.springkafka.consumer;

import br.com.moser.springkafka.People;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Moser
 */
@Slf4j
@Component
public class PeopleConsumer {

    @KafkaListener(topics = "${topic.name}")
    public void consumer(ConsumerRecord<String, People> record, Acknowledgment ack) {

        var people = record.value();

        log.info("Mensagem consumida " +people.toString());

        ack.acknowledge();
    }
}

package br.com.moser.springkafka.consumer;

import br.com.moser.springkafka.People;
import br.com.moser.springkafka.domain.Book;
import br.com.moser.springkafka.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author Juliano Moser
 */
@Slf4j
@Component
@AllArgsConstructor
public class PeopleConsumer {

    private final PeopleRepository peopleRepository;

    @KafkaListener(topics = "${topic.name}")
    public void consumer(ConsumerRecord<String, People> record, Acknowledgment ack) {

        var people = record.value();

        log.info("Mensagem consumida " +people.toString());

        var peopleEntity = new br.com.moser.springkafka.domain.People();

        peopleEntity.setId(people.getId().toString());
        peopleEntity.setName(people.getName().toString());
        peopleEntity.setCpf(people.getCpf().toString());
        peopleEntity.setBooks(people.getBooks().stream().map(book -> Book.builder()
                .people(peopleEntity)
                .nome(book.toString())
                .build()).collect(Collectors.toList()));


        peopleRepository.save(peopleEntity);

        ack.acknowledge();
    }
}

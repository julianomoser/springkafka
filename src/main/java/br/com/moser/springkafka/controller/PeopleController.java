package br.com.moser.springkafka.controller;

import br.com.moser.springkafka.People;
import br.com.moser.springkafka.controller.dto.PeopleDTO;
import br.com.moser.springkafka.producer.PeopleProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Juliano Moser
 */
@Controller
@RequestMapping("/peoples")
@AllArgsConstructor
public class PeopleController {

    private final PeopleProducer peopleProducer;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody PeopleDTO peopleDTO) {

        var message = People.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setName(peopleDTO.getName())
                .setCpf(peopleDTO.getCpf())
                .setBooks(peopleDTO.getBooks().stream().map(p -> (CharSequence) p).collect(Collectors.toList()))
                .build();

        peopleProducer.sendMessage(message);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

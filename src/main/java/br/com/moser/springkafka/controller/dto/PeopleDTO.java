package br.com.moser.springkafka.controller.dto;

import lombok.Getter;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Getter
public class PeopleDTO {

 private String name;
 private String cpf;

 private List<String> books;
}

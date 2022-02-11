package br.com.moser.springkafka.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author Juliano Moser
 */
@Entity
public class People {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nome;
    private String cpf;

    @OneToMany(mappedBy = "people")
    private List<Book> books;
}

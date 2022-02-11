package br.com.moser.springkafka.repository;

import br.com.moser.springkafka.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, String> {
}
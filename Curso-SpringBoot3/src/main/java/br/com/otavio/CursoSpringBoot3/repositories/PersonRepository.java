package br.com.otavio.CursoSpringBoot3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otavio.CursoSpringBoot3.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}

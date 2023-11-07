package br.com.otavio.CursoSpringBoot3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otavio.CursoSpringBoot3.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long>{
}

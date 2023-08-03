package br.com.otavio.CursoSpringBoot3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otavio.CursoSpringBoot3.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}

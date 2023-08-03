package br.com.otavio.CursoSpringBoot3.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.otavio.CursoSpringBoot3.controllers.BookController;
import br.com.otavio.CursoSpringBoot3.exceptions.RequiredObjetctIsNullException;
import br.com.otavio.CursoSpringBoot3.exceptions.ResourceNotFoundException;
import br.com.otavio.CursoSpringBoot3.mapper.DozerMapper;
import br.com.otavio.CursoSpringBoot3.model.Book;
import br.com.otavio.CursoSpringBoot3.repositories.BookRepository;
import br.com.otavio.CursoSpringBoot3.vo.v1.BookVO;

@Service
public class BookService {

    private Logger logger = Logger.getLogger(BookService.class.getName());
    
    @Autowired
    private BookRepository repository;

    
    public BookVO findById(Long id) {
    	
    	logger.info("Fiding one persin!");
    	
    	var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	var vo = DozerMapper.parseObject(entity, BookVO.class);
    	
    	vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
    	
    	return vo;
    }
    
    public List<BookVO> findAll() {
    	
    	logger.info("Fiding all persin!");
    	
    	var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
    	
    	books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
    	
    	return books;
    }
    
    public BookVO create(BookVO book) {
    	
    	if(book == null) throw new RequiredObjetctIsNullException();
    	
    	logger.info("Creating one Obook");
    	
    	var entitiy = DozerMapper.parseObject(book, Book.class);
    	
    	entitiy = repository.save(entitiy);  
    	
    	var vo = DozerMapper.parseObject(entitiy, BookVO.class);
    	
    	vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
    	
    	return vo;
    }
    
    public BookVO update(BookVO book) {
    	
    	if(book == null) throw new RequiredObjetctIsNullException();
    	
    	logger.info("uptadeting one Obook");
    	
    	var entity = repository.findById(book.getKey())
    			.orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	entity.setAuthor(book.getAuthor());
    	entity.setLaunchDate(book.getLaunchDate());
    	entity.setPrice(book.getPrice());
    	entity.setTitle(book.getTitle());
    	
    	var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
    	
    	vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
    	
    	return vo;
    }
    
    public void delete(Long id) {
    	logger.info("Deleting one book");
    	
    	var entity = repository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	repository.delete(entity);
    }

}

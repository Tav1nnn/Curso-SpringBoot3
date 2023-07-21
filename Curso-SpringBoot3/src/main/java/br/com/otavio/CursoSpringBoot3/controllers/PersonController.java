package br.com.otavio.CursoSpringBoot3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.otavio.CursoSpringBoot3.services.PersonService;
import br.com.otavio.CursoSpringBoot3.vo.v1.PersonVO;

@RestController()
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
    private PersonService service;
	
	@GetMapping(value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,//Consome
		produces = MediaType.APPLICATION_JSON_VALUE//Produz
			)
	public PersonVO creating(@RequestBody PersonVO person) {
		return service.create(person);
	}
	
	@PutMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,//Consome
			produces = MediaType.APPLICATION_JSON_VALUE//Produz
			)
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		 
		return ResponseEntity.noContent().build();
	}
}

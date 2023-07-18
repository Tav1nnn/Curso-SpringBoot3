package br.com.otavio.CursoSpringBoot3.controllers;

import br.com.otavio.CursoSpringBoot3.model.Person;
import br.com.otavio.CursoSpringBoot3.services.PersonService;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
    private PersonService service;
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") String id) {
		return service.findById(id);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE,//Consome
		produces = MediaType.APPLICATION_JSON_VALUE//Produz
			)
	public Person creating(@RequestBody Person person) {
		return service.create(person);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,//Consome
			produces = MediaType.APPLICATION_JSON_VALUE//Produz
			)
	public Person update(@RequestBody Person person) {
		return service.update(person);
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE
			)
	public void delete(@RequestParam(value = "id") String id) {
		
	}
}

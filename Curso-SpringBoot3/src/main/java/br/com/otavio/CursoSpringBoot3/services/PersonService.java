package br.com.otavio.CursoSpringBoot3.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.CursoSpringBoot3.exceptions.ResourceNotFoundException;
import br.com.otavio.CursoSpringBoot3.model.Person;
import br.com.otavio.CursoSpringBoot3.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());
    
    @Autowired
    private PersonRepository repository;
    
    public Person findById(Long id) {
    	
    	logger.info("Fiding one persin!");
    	
    	return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
    }
    
    public List<Person> findAll() {
    	
    	logger.info("Fiding all persin!");
    	
    	return repository.findAll();
    }
    
    public Person create(Person person) {
    	
    	logger.info("Creating one Operson");
    	
    	person = repository.save(person);
    	
    	return person;
    }
    
    public Person update(Person person) {
    	logger.info("uptadeting one Operson");
    	
    	var entity = repository.findById(person.getId())
    			.orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	entity.setFirstName(person.getFirstName());
    	entity.setLastname(person.getLastname());
    	entity.setAddress(person.getAddress());
    	entity.setGender(person.getGender());
    	
    	return repository.save(entity);
    }
    
    public void delete(Long id) {
    	logger.info("Deleting one person");
    	
    	var entity = repository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	repository.delete(entity);
    }

}

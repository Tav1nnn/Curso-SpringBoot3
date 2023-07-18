package br.com.otavio.CursoSpringBoot3.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.otavio.CursoSpringBoot3.model.Person;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());
    
    public Person findById(String id) {
    	
    	logger.info("Fiding one persin!");
    	
    	Person person = new Person(
    			counter.incrementAndGet(), 
    			"Otavio",
    			"Marques",
    			"Moreira Sales - Pr - Br",
    			"M"
    			);
    	
    	return person;
    }
    
    public List<Person> findAll() {
    	
    	logger.info("Fiding all persin!");
    	
    	List<Person> list = new ArrayList<>();
    	
    	for(int i = 0; i<8; i++) {

        	Person person = mockPerson(i);
        	
        	list.add(person);
        
    	}
    	
    	return list;
    }
    
    public Person create(Person person) {
    	person.setId(counter.incrementAndGet());
    	logger.info("Creating one Operson");
    	
    	return person;
    }
    
    public Person update(Person person) {
    	logger.info("uptadeting one Operson");
    	person.setId(counter.incrementAndGet());
    	
    	return person;
    }
    
    public void delete(String id) {
    	logger.info("Deleting one person");
    	
    	
    }

	private Person mockPerson(int i) {
		Person person = new Person(
    			counter.incrementAndGet(), 
    			"Otavio" +i,
    			"Marques" +i,
    			"Moreira Sales - Pr - Br",
    			"M"
    			);
		
		return person;
	}

}

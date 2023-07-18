package br.com.otavio.CursoSpringBoot3.services;

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

}

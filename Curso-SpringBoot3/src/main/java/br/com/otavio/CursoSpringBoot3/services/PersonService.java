package br.com.otavio.CursoSpringBoot3.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.CursoSpringBoot3.exceptions.ResourceNotFoundException;
import br.com.otavio.CursoSpringBoot3.mapper.DozerMapper;
import br.com.otavio.CursoSpringBoot3.model.Person;
import br.com.otavio.CursoSpringBoot3.repositories.PersonRepository;
import br.com.otavio.CursoSpringBoot3.vo.v1.PersonVO;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());
    
    @Autowired
    private PersonRepository repository;
    
    public PersonVO findById(Long id) {
    	
    	logger.info("Fiding one persin!");
    	
    	var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	return DozerMapper.parseObject(entity, PersonVO.class);
    }
    
    public List<PersonVO> findAll() {
    	
    	logger.info("Fiding all persin!");
    	
    	return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }
    
    public PersonVO create(PersonVO person) {
    	
    	logger.info("Creating one Operson");
    	
    	var entitiy = DozerMapper.parseObject(person, Person.class);
    	
    	entitiy = repository.save(entitiy);  
    	
    	return DozerMapper.parseObject(entitiy, PersonVO.class);
    }
    
    public PersonVO update(PersonVO person) {
    	logger.info("uptadeting one Operson");
    	
    	var entity = repository.findById(person.getId())
    			.orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	entity.setFirstName(person.getFirstName());
    	entity.setLastname(person.getLastname());
    	entity.setAddress(person.getAddress());
    	entity.setGender(person.getGender());
    	
    	return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }
    
    public void delete(Long id) {
    	logger.info("Deleting one person");
    	
    	var entity = repository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	repository.delete(entity);
    }

}

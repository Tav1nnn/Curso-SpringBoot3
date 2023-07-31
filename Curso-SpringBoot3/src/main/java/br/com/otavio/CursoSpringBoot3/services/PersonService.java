package br.com.otavio.CursoSpringBoot3.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.otavio.CursoSpringBoot3.controllers.PersonController;
import br.com.otavio.CursoSpringBoot3.exceptions.ResourceNotFoundException;
import br.com.otavio.CursoSpringBoot3.mapper.DozerMapper;
import br.com.otavio.CursoSpringBoot3.mapper.custom.PersonMapper;
import br.com.otavio.CursoSpringBoot3.model.Person;
import br.com.otavio.CursoSpringBoot3.repositories.PersonRepository;
import br.com.otavio.CursoSpringBoot3.vo.v1.PersonVO;
import br.com.otavio.CursoSpringBoot3.vo.v2.PersonVO2;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());
    
    @Autowired
    private PersonRepository repository;
    
    @Autowired
    private PersonMapper mapper;
    
    public PersonVO findById(Long id) {
    	
    	logger.info("Fiding one persin!");
    	
    	var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
    	
    	vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    	
    	return vo;
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
    
    public PersonVO2 createV2(PersonVO2 person) {
    	
    	logger.info("Creating one Operson");
    	
    	var entitiy = mapper.convertVoToEntity(person);
    	
    	entitiy = repository.save(entitiy);  
    	
    	return mapper.convertEntityToVo(entitiy);
    }
    
    public PersonVO update(PersonVO person) {
    	logger.info("uptadeting one Operson");
    	
    	var entity = repository.findById(person.getKey())
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

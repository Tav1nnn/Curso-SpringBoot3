package br.com.otavio.CursoSpringBoot3.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.otavio.CursoSpringBoot3.controllers.PersonController;
import br.com.otavio.CursoSpringBoot3.exceptions.RequiredObjetctIsNullException;
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
    	
    	logger.info("Service: Fiding one persin!");
    	
    	var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	var vo = DozerMapper.parseObject(entity, PersonVO.class);
    	
    	vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    	
    	return vo;
    }
    
    public List<PersonVO> findAll() {
    	
    	logger.info("Service: Fiding all persin!");
    	
    	var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    	
    	persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
    	
    	return persons;
    }
    
    public PersonVO create(PersonVO person) {
    	
    	if(person == null) throw new RequiredObjetctIsNullException();
    	
    	logger.info("Service: Creating one Operson");
    	
    	var entitiy = DozerMapper.parseObject(person, Person.class);
    	
    	entitiy = repository.save(entitiy);  
    	
    	var vo = DozerMapper.parseObject(entitiy, PersonVO.class);
    	
    	vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
    	
    	return vo;
    }
    
    public PersonVO2 createV2(PersonVO2 person) {
    	
    	if(person == null) throw new RequiredObjetctIsNullException();
    	
    	logger.info("Service: Creating one Operson");
    	
    	var entitiy = mapper.convertVoToEntity(person);
    	
    	entitiy = repository.save(entitiy);  
    	
    	return mapper.convertEntityToVo(entitiy);
    }
    
    public PersonVO update(PersonVO person) {
    	
    	if(person == null) throw new RequiredObjetctIsNullException();
    	
    	logger.info("Service: Uptadeting one Operson");
    	
    	var entity = repository.findById(person.getKey())
    			.orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	entity.setFirstName(person.getFirstName());
    	entity.setLastname(person.getLastname());
    	entity.setAddress(person.getAddress());
    	entity.setGender(person.getGender());
    	
    	var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    	
    	vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
    	
    	return vo;
    }
    
    public void delete(Long id) {
    	logger.info("Deleting one person");
    	
    	var entity = repository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("id not found"));
    	
    	repository.delete(entity);
    }

}

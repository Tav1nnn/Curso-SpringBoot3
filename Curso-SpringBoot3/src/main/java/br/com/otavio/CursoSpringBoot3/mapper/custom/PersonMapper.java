package br.com.otavio.CursoSpringBoot3.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.otavio.CursoSpringBoot3.model.Person;
import br.com.otavio.CursoSpringBoot3.vo.v2.PersonVO2;

@Service
public class PersonMapper {
	
	public PersonVO2 convertEntityToVo(Person person) {
		
		PersonVO2 vo = new PersonVO2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastname(person.getLastname());
		vo.setAddress(person.getAddress());
		vo.setGender(person.getGender());
		vo.setBirthDay(new Date());
		
		return vo;
	}
	
	public Person convertVoToEntity(PersonVO2 person) {
		
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastname(person.getLastname());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return entity;
	}
}

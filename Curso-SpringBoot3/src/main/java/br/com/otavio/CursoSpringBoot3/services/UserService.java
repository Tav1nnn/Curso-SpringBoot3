package br.com.otavio.CursoSpringBoot3.services;

import br.com.otavio.CursoSpringBoot3.controllers.PersonController;
import br.com.otavio.CursoSpringBoot3.exceptions.RequiredObjetctIsNullException;
import br.com.otavio.CursoSpringBoot3.exceptions.ResourceNotFoundException;
import br.com.otavio.CursoSpringBoot3.mapper.DozerMapper;
import br.com.otavio.CursoSpringBoot3.mapper.custom.PersonMapper;
import br.com.otavio.CursoSpringBoot3.model.Person;
import br.com.otavio.CursoSpringBoot3.repositories.PersonRepository;
import br.com.otavio.CursoSpringBoot3.repositories.UserRepository;
import br.com.otavio.CursoSpringBoot3.vo.v1.PersonVO;
import br.com.otavio.CursoSpringBoot3.vo.v2.PersonVO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserService.class.getName());
    
    @Autowired
    private UserRepository repository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Finding one user by name " +username+ "!");

		var user = repository.findByUserName(username);

		if(user != null){
			return user;
		}else {
			throw new UsernameNotFoundException("Username " + username + " not found! ");
		}
	}
}

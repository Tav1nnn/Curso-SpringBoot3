package br.com.otavio.CursoSpringBoot3.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.otavio.CursoSpringBoot3.services.PersonService;
import br.com.otavio.CursoSpringBoot3.util.MediaType;
import br.com.otavio.CursoSpringBoot3.vo.v1.PersonVO;
import br.com.otavio.CursoSpringBoot3.vo.v2.PersonVO2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
	
	@Autowired
    private PersonService service;

	private Logger logger = Logger.getLogger(PersonController.class.getName());

	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a People", description = "Finds a People",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200", 
			content = @Content(schema = @Schema(implementation = PersonVO.class))),
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		logger.info("Service: Fiding one persin!");
		return service.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all People", description = "Finds all People",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200", 
			content = {
				@Content(
					mediaType = "application/json",
						array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
					)
		}),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PagedModel<EntityModel<PersonVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size,
			@RequestParam(value = "sort", defaultValue = "asc") String sort

	) {

		Direction sortDirection = "desc".equalsIgnoreCase(sort) ? Direction.DESC : Direction.ASC;


		logger.info("Service: Fiding all persin!");

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));
		return null;
	}

	@PostMapping(
		consumes = MediaType.APPLICATION_JSON,//Consome
		produces = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a People", description = "Adds a People",
			tags = {"People"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(schema = @Schema(implementation = PersonVO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PersonVO creating(@RequestBody PersonVO person) {
		logger.info("Service: Creating one Operson");
		return service.create(person);
	}
	
	@PostMapping(
			value = "/v2",
			consumes = MediaType.APPLICATION_JSON,//Consome
			produces = {MediaType.APPLICATION_JSON,
						MediaType.APPLICATION_XML,
						MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a People", description = "Adds a People",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Success", responseCode = "200", 
			content = @Content(schema = @Schema(implementation = PersonVO.class))),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PersonVO2 creatingV2(@RequestBody PersonVO2 person) {
		logger.info("Service: Creating one Operson");
		return service.createV2(person);
	}
	
	@PutMapping(
			consumes = MediaType.APPLICATION_JSON,//Consome
			produces = {MediaType.APPLICATION_JSON,
						MediaType.APPLICATION_XML,
						MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a People", description = "Updates  a People",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "Updated", responseCode = "200", 
			content = @Content(schema = @Schema(implementation = PersonVO.class))),
		
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PersonVO update(@RequestBody PersonVO person) {
		logger.info("Service: Uptadeting one Operson");
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a People", description = "Deletes a People",
	tags = {"People"},
	responses = {
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		logger.info("Deleting one person");
		service.delete(id);
		 
		return ResponseEntity.noContent().build();
	}

}

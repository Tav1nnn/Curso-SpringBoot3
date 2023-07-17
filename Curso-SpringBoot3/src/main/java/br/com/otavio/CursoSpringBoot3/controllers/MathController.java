package br.com.otavio.CursoSpringBoot3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.otavio.CursoSpringBoot3.converters.NumberConverter;
import br.com.otavio.CursoSpringBoot3.exceptions.UnsupportedMathOperationException;
import br.com.otavio.CursoSpringBoot3.math.SimpleMath;

@RestController
public class MathController {
	
	SimpleMath math = new SimpleMath();
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo
			) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.sum(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(
			@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo
			) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.sub(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@GetMapping("/mult/{numberOne}/{numberTwo}")
	public Double mult(
			@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo
			) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.mult(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@GetMapping("/div/{numberOne}/{numberTwo}")
	public Double div(
			@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo
			) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.div(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
	}
	
	@GetMapping("/raiz/{numberOne}")
	public Double raiz(
			@PathVariable("numberOne") String numberOne
			) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.raiz(NumberConverter.convertToDouble(numberOne));
	}
	
}

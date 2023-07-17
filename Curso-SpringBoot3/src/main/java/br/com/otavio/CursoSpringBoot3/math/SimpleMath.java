package br.com.otavio.CursoSpringBoot3.math;


public class SimpleMath {
	
	public Double sum(Double numberOne, Double numberTwo){
		
		
		return numberOne + numberTwo;
	}
	
	public Double sub (Double numberOne, Double numberTwo){
		
		
		return numberOne - numberTwo;
	}
	
	public Double mult(Double numberOne, Double numberTwo){
		
		return numberOne * numberTwo;
	}
	
	public Double div(Double numberOne, Double numberTwo){
		
		return numberOne / numberTwo;
	}
	
	public Double raiz(Double numberOne) throws Exception {
		
	
		return Math.sqrt(numberOne);
	}
}

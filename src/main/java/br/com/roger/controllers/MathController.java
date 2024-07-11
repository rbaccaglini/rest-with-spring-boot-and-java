package br.com.roger.controllers;

import java.util.concurrent.atomic.AtomicLong;

import br.com.roger.services.MathService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
	private static final AtomicLong counter = new AtomicLong();
	private final MathService service = new MathService();
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo) throws Exception {

		return service.sum(numberOne, numberTwo);
	}

	@RequestMapping(value="/sub/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sub(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo) throws Exception {
		return service.subtraction(numberOne, numberTwo);
	}

	@RequestMapping(value="/mult/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double mult(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo) throws Exception {
		return service.multiplication(numberOne, numberTwo);
	}

	@RequestMapping(value="/div/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double div(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo) throws Exception {

		return service.division(numberOne, numberTwo);
	}

	@RequestMapping(value="/med/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double med(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo) throws Exception {

		return service.average(numberOne, numberTwo);
	}

	@RequestMapping(value="/root/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double root(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo) throws Exception {
		return service.root(numberOne, numberTwo);
	}

	private Double convertToDouble(String number) {
		if(!isNumeric(number)) {
			return 0D;
		}
		
		String strNumber = number.replaceAll(",", ".");
		return Double.parseDouble(strNumber);
	}

	private static boolean isNumeric(String number) {
		if (number == null) {
			return false;
		}
		String strNumber = number.replaceAll(",", ".");
        return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}

package br.com.roger;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
	private static final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
			throw new Exception();
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
		
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
        try {
            Double.parseDouble(strNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
	}
	
}

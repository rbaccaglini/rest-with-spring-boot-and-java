package br.com.roger.services;

import org.springframework.web.bind.annotation.PathVariable;

public class MathService {

    public Double sum(String numberOne, String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    public Double subtraction(String numberOne, String numberTwo){

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    public Double multiplication(String numberOne, String numberTwo) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    public Double division(String numberOne, String numberTwo) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        Double dNumberOne = convertToDouble(numberOne);
        Double dNumberTwo = convertToDouble(numberTwo);

        if(dNumberTwo == 0){
            throw new UnsupportedOperationException("Division by zero error!");
        }

        return dNumberOne / dNumberTwo;
    }

    public Double average(String numberOne, String numberTwo) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        Double dNumberOne = convertToDouble(numberOne);
        Double dNumberTwo = convertToDouble(numberTwo);

        return (dNumberOne + dNumberTwo) / 2D;
    }

    public Double root(String numberOne, String numberTwo) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value!");
        }

        Double dNumberOne = convertToDouble(numberOne);
        Double dNumberTwo = convertToDouble(numberTwo);

        return Math.pow(dNumberOne, dNumberTwo);
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

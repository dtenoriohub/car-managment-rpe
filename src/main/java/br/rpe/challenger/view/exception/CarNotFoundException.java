package br.rpe.challenger.view.exception;

public class CarNotFoundException extends Exception{

    public CarNotFoundException(String type, String fieldName, String fieldValue){
        super(String.format("Could not find a %s with %s = '%s'",type, fieldName, fieldValue));
    }

}

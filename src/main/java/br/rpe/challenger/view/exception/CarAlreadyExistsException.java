package br.rpe.challenger.view.exception;

public class CarAlreadyExistsException extends Exception{

    public CarAlreadyExistsException(String type, String fieldName, String fieldValue){
        super(String.format("A %s with %s = '%s' already exists",type, fieldName, fieldValue));
    }
}

package br.com.giulianabezerra.picpaydesafiobackend.exception;



public class InvalidTransactionException extends RuntimeException{
	
	public InvalidTransactionException(String message) {
		super(message);
	}
}

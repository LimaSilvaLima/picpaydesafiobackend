package br.com.giulianabezerra.picpaydesafiobackend.authotization;


public class UnauthorizedTransactionException extends RuntimeException{
	
	public UnauthorizedTransactionException(String message) {
		super(message);
	}
	
}

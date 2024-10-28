package br.com.giulianabezerra.picpaydesafiobackend.authotization;

import br.com.giulianabezerra.picpaydesafiobackend.transaction.Transaction;
//import br.com.giulianabezerra.picpaydesafiobackend.transaction.UnauthorizedTransactionException;
//package br.com.giulianabezerra.picpaydesafiobackend.authorization;

//import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Transaction;
import org.springframework.stereotype.Service;

import br.com.giulianabezerra.picpaydesafiobackend.exception.InvalidTransactionException;

@Service
public class AuthorizerService {

    public void authorize(Transaction transaction){
        if (transaction.payer().equals(transaction.payee())) {
            throw new InvalidTransactionException("payer and cannot be the same");
            
        }
    }

}

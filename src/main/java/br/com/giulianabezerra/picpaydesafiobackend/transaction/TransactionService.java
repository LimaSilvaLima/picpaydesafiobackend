package br.com.giulianabezerra.picpaydesafiobackend.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.giulianabezerra.picpaydesafiobackend.authotization.AuthorizerService;
import br.com.giulianabezerra.picpaydesafiobackend.exception.InvalidTransactionException;
import br.com.giulianabezerra.picpaydesafiobackend.wallet.Wallet;
import br.com.giulianabezerra.picpaydesafiobackend.wallet.WalletRepository;
import br.com.giulianabezerra.picpaydesafiobackend.wallet.WalletType;



@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final  WalletRepository walletRepository;
    private final AuthorizerService authorizerService;


    public TransactionService (TransactionRepository transactionRepository, 
    WalletRepository walletRepository, AuthorizerService authorizerService){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
    }

    @Transactional
    public Transaction create(Transaction transaction){
        // 1 - validar
        validate(transaction);

        //2 - criart a transação
        var newTransaction = transactionRepository.save(transaction);
        
        // 3 - debitar da ccarteira

        var wallet = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(wallet.debit(transaction.value()));
        // 4 - chamar serviços externos

        return newTransaction;


    }

    private void validate(Transaction transaction) {
    	walletRepository.findById(transaction.payee())
            .map(payee -> walletRepository.findById(transaction.payer())
                .map(payer -> isTransactionValid(transaction, payer) ? transaction : null )
                .orElseThrow(()-> new InvalidTransactionException("Invalid trnasaction - %s".formatted(transaction))))
            .orElseThrow(()-> new InvalidTransactionException("Invalid transaction - %s".formatted(transaction)));
            
            
    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getValue() &&
            payer.balance().compareTo(transaction.value()) >= 0 &&
                !payer.id().equals(transaction.payee());
    }

}

package com.tata.demo.infrastructure.servicesimpl;

import com.tata.demo.application.utils.BasicResponse;
import com.tata.demo.core.enums.TransactionType;
import com.tata.demo.core.model.TransactionModel;
import com.tata.demo.core.services.TransactionService;
import com.tata.demo.infrastructure.entities.AccountEntity;
import com.tata.demo.infrastructure.entities.TransactionEntity;
import com.tata.demo.infrastructure.repositories.AccountRepository;
import com.tata.demo.infrastructure.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl<T> implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Iterable<?> findAll() {
        return null;
    }

    @Override
    public Optional<?> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public T save(TransactionModel transaction) {
        BasicResponse response = new BasicResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        String das = sdf.format(new Date());
        Optional<TransactionEntity> transactionDB = Optional.empty();
        Optional<AccountEntity> account = accountRepository.findById(transaction.getAccountId());
        System.out.println(account.get().getAccountNumber());
//        AccountEntity account = accountRepository.findById(transaction.getAccountId()).get();
        Double balance = transactionRepository.lastBalance(account.get().getAccountNumber());

//        ErrorDto error = new ErrorDto();
        switch (transaction.getTransactionType().toString().toLowerCase()) {
            case "retiro":
                if (transactionRepository.validateInitialAmount(account.get().getAccountNumber())) {
                    if (balance >= transaction.getAmount()) {
                        transactionDB = Optional.of(TransactionEntity.builder()
                                        .transactionType(TransactionType.Retiro)
                                        .amount(transaction.getAmount())
                                        .account(account.get())
                                        .balance(balance - transaction.getAmount())
                                .build());

                        transactionDB = Optional.of(transactionRepository.save(transactionDB.get()));

                        response = BasicResponse.builder()
                                .code(200)
                                .status("Ok")
                                .message("Transaccion realizada con exito" + transactionDB.get().getId())
                                .build();
                    } else {
                        response = BasicResponse.builder()
                                .code(403)
                                .status("Failed")
                                .message("Monto excede disponible")
                                .build();
                    }
                }
                if (transactionRepository.validateDailyAmount(account.get().getAccountNumber()) &&
                        !transactionRepository.validateInitialAmount(account.get().getAccountNumber())) {
                    if (balance >= transaction.getAmount()) {
                        transactionDB = Optional.of(TransactionEntity.builder()
                                .transactionType(TransactionType.Retiro)
                                .amount(transaction.getAmount())
                                .account(account.get())
                                .balance(balance - transaction.getAmount())
                                .build());

                        transactionDB = Optional.of(transactionRepository.save(transactionDB.get()));
                        response = BasicResponse.builder()
                                .code(200)
                                .status("Ok")
                                .message("Transaccion realizada con exito" + transactionDB.get().getId())
                                .build();
                    } else {
                        response = BasicResponse.builder()
                                .code(403)
                                .status("Failed")
                                .message("Monto excede disponible")
                                .build();
                    }
                } else {
                    response = BasicResponse.builder()
                            .code(403)
                            .status("Failed")
                            .message("Cupo diario excedido")
                            .build();
                }

                break;
            case "deposito":
                transactionDB = Optional.of(TransactionEntity.builder()
                                .transactionType(TransactionType.Deposito)
                                .balance(balance + transaction.getAmount())
                                .amount(transaction.getAmount())
                                .account(account.get())
                        .build());

                transactionDB = Optional.of(transactionRepository.save(transactionDB.get()));
                response = BasicResponse.builder()
                        .code(200)
                        .status("Ok")
                        .message("Transaccion realizada con exito: " + transactionDB.get().getId())
                        .build();
                break;
            default:
                response = BasicResponse.builder()
                        .code(403)
                        .status("Failed")
                        .message("Tipo transaccion no encontrada")
                        .build();
                break;
        }

        return (T) response;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List accountStatus(String accountNumber, Date initDate, Date endDate) throws ParseException {
        return List.of();
    }
}

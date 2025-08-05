package com.tata.demo.infrastructure.servicesimpl;

import com.tata.demo.application.struct.AccountMapper;
import com.tata.demo.application.utils.BasicResponse;
import com.tata.demo.application.utils.ObjectsResponse;
import com.tata.demo.core.enums.TransactionType;
import com.tata.demo.core.model.AccountModel;
import com.tata.demo.core.services.AccountCRUDServices;
import com.tata.demo.core.services.AccountService;
import com.tata.demo.infrastructure.entities.AccountEntity;
import com.tata.demo.infrastructure.entities.ClientEntity;
import com.tata.demo.infrastructure.entities.TransactionEntity;
import com.tata.demo.infrastructure.repositories.AccountRepository;
import com.tata.demo.infrastructure.repositories.ClientRepository;
import com.tata.demo.infrastructure.repositories.PersonRepository;
import com.tata.demo.infrastructure.repositories.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
public class AccountServiceImpl<T> implements AccountService {

    @Autowired
    private AccountRepository accRepository;

    @Autowired
    private ClientRepository clRepository;

    @Autowired
    private TransactionRepository trRepository;

    @Autowired
    private AccountMapper accMapper;


    @Override
    public Iterable<?> findAll() {
        return null;
    }

    @Override
    public Optional<?> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public T findByAccountNumber(String accountNumber) {
        Optional<AccountEntity> accEntity = accRepository.findByAccountNumber(accountNumber);

        try {
            if (accEntity.isEmpty()) {
                return (T) BasicResponse.builder()
                        .code(404)
                        .status("Not found")
                        .message("Registro no encontrado... ")
                        .build();
            }

            return (T) ObjectsResponse.builder()
                    .code(200)
                    .status("Ok")
                    .message("Registro encontrado... ")
                    .object(accMapper.entityToModel(accEntity.get()))
                    .build();

        } catch (Exception e) {
            log.error("Error al consultar... " + accountNumber);
            return (T) BasicResponse.builder()
                    .code(400)
                    .status("Error")
                    .message("Error al consultar... ")
                    .build();

        }
    }

    @Override
    public T save(AccountModel account) {

        Optional<AccountEntity> accEntity = Optional.empty();
        List<ClientEntity> clSet = new ArrayList<>();

        try {

            account.getClientsId().forEach(obj -> {
                Optional<ClientEntity> clEnt = clRepository.findById(obj);
                if (!clEnt.isEmpty()) {
                    clSet.add(clEnt.get());
                }
            });

            if (clSet.isEmpty()) {
                return (T) BasicResponse.builder()
                        .code(404)
                        .status("Not found")
                        .message("Cliente no encontrado...")
                        .build();
            }

            accEntity = Optional.ofNullable(accMapper.modelToEntity(account));
            accEntity.get().setClients(clSet);
            accEntity.get().setId(null);

            accEntity = Optional.ofNullable(accRepository.save(accEntity.get()));

            TransactionEntity transaction = TransactionEntity.builder()
                    .transactionType(TransactionType.Deposito)
                    .balance(accEntity.get().getInitialBalance())
                    .amount(accEntity.get().getInitialBalance())
                    .account(accEntity.get())
                    .initial(Boolean.TRUE)
                    .build();
            transaction = trRepository.save(transaction);
            List<TransactionEntity> transactions = new ArrayList<>();
            transactions.add(transaction);

            return (T) ObjectsResponse.builder()
                    .code(201)
                    .status("Created")
                    .message("Cuenta creada con exito...")
                    .object(accEntity)
                    .build();

        } catch (Exception e) {
            return (T) BasicResponse.builder()
                    .code(400)
                    .status("Error")
                    .message("Error al guardar registro...")
                    .build();
        }
    }

    @Override
    public T update(AccountModel account) {
        return null;
    }

    @Override
    public Object deleteById(Long id) {
        return null;
    }
}

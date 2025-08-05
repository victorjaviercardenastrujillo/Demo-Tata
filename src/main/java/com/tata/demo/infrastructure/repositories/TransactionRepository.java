package com.tata.demo.infrastructure.repositories;

import com.tata.demo.infrastructure.entities.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

    @Query(value = "select case when cast((select u.value from tata.utils u where u.name='limite diario de retiro') as float) " +
            "> sum(t.amount) " +
            "then true else false end " +
            "from tata.transactions t inner join tata.accounts a on a.id = t.account_id " +
            "where cast(t.transaction_date as date) = (current_date) " +
            " and t.transaction_type ='Retiro' " +
            "and a.account_number=:accountNumber "
            , nativeQuery = true)
    public Boolean validateDailyAmount(@Param("accountNumber") String accountNumber);

    @Query(value = "select t.initial from tata.transactions t where t.id=(" +
            "select max(t.id) from tata.transactions t inner join tata.accounts a on a.id = t.account_id " +
            "where a.account_number=:accountNumber)"
            , nativeQuery = true)
    public Boolean validateInitialAmount(@Param("accountNumber") String accountNumber);

    @Query(value = "select t.balance from tata.transactions t where t.id=(" +
            "select max(t.id) from tata.transactions t inner join tata.accounts a on a.id = t.account_id " +
            "where a.account_number=:accountNumber)"
            , nativeQuery = true)
    public Double lastBalance(@Param("accountNumber") String accountNumber);
//
//    @Query(value = "SELECT a.clients.dni " +
//            "FROM Transaction t  " +
//            "WHERE " +
//            "a.accountNumber=:accountNumber "
//           + " AND " +
//            "cast(t.transactionDate as date) between :initDate AND :endDate "
//    )
//    public List<Object[]> accountStatus(
////            String  accountNumber,
//             Date initDate, Date endDate
//    );
}

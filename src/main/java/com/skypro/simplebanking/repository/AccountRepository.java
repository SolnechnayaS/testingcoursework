package com.skypro.simplebanking.repository;

import com.skypro.simplebanking.entity.Account;
import java.util.Optional;

import com.skypro.simplebanking.entity.AccountCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> getAccountByUser_IdAndId(Long userId, Long accountId);

  @Query(value = "SELECT id FROM accounts " +
          "WHERE account_currency= :currency " +
          "AND user_id= :userId",
          nativeQuery = true)
  Long getAccountByUserId_and_Currency (Long userId, int currency);

  @Query(value = "SELECT MAX(id) FROM accounts",
          nativeQuery = true)
  Long findLastAccountId ();
}

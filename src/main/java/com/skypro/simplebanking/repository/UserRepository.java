package com.skypro.simplebanking.repository;

import com.skypro.simplebanking.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  @Query(value = "SELECT MAX(id) FROM users",
          nativeQuery = true)
  Long findLastUserId ();
}

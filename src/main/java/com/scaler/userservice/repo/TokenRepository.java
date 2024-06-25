package com.scaler.userservice.repo;

import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Token save(Token t);

   // @Query(value="select * from Token where token = token and is_valid = valid",nativeQuery = true)
    Optional<Token> findByTokenAndIsValid(String token, boolean valid);
     List<Token> findByUserAndIsValidAndExpiryDateGreaterThan(User user, boolean b, Date currentDate);

     Optional<Token> findByTokenAndIsValidAndExpiryDateGreaterThan(String token,boolean b,Date currentDate);
}

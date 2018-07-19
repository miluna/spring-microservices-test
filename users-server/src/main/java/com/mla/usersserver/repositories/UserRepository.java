package com.mla.usersserver.repositories;

import com.mla.usersserver.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM user WHERE username = :name AND password = :password", nativeQuery = true)
    Optional<UserEntity> findUserEntityByUserAndPass(@Param("name") String name, @Param("password") String password);



}

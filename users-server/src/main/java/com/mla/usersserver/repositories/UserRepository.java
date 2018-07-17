package com.mla.usersserver.repositories;

import com.mla.usersserver.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT * FROM user WHERE user = :user AND pass = :pass")
    UserEntity findUserEntityByUserAndPass(@Param("user") String user, @Param("pass") String pass);



}

package com.example.demo.login.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.login.Entity.RoleEnum;
import com.example.demo.login.Entity.UserEntity;

@Repository

public interface UserRepository  extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByUsername(String username);

    @Query("SELECT u FROM UserEntity u JOIN FETCH u.persona")
    List<UserEntity> findAllWithPersona();
    List<UserEntity> findByRoles_RoleEnum(RoleEnum roleEnum);

    Optional<UserEntity> findByUsername(String username);

}

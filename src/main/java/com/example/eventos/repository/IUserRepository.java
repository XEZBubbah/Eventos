package com.example.eventos.repository;

import com.example.eventos.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository <UserEntity,Long> {

    public Optional<UserEntity> findOneByUser(String user);
}

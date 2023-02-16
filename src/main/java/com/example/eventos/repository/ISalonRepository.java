package com.example.eventos.repository;

import com.example.eventos.entity.SalonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalonRepository extends JpaRepository <SalonEntity,Long> {

}

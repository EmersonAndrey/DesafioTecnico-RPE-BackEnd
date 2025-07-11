package com.rpe.desafioTecnico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rpe.desafioTecnico.model.entities.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

}

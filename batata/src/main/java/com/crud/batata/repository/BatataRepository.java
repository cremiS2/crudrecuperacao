package com.crud.batata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.batata.model.BatataModel;

public interface BatataRepository extends JpaRepository<BatataModel, Long> {

}

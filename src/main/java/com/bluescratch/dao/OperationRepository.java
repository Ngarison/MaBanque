package com.bluescratch.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bluescratch.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	
	@Query("SELECT o FROM Operation o WHERE o.comptes.codeCompte like :x order by o.dateOperation desc")
	public  Page listOperation(@Param("x")String codeCpte, Pageable pageable);

}

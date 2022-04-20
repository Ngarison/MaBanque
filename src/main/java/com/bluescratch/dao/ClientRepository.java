package com.bluescratch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluescratch.entities.Client;

public interface ClientRepository extends JpaRepository<Client,Long>{
	

}

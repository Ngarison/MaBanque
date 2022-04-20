package com.bluescratch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.bluescratch.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String>{

}

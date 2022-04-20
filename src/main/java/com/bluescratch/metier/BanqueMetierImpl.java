package com.bluescratch.metier;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluescratch.dao.CompteRepository;
import com.bluescratch.dao.OperationRepository;
import com.bluescratch.entities.Compte;
import com.bluescratch.entities.CompteCourant;
import com.bluescratch.entities.Operation;
import com.bluescratch.entities.Retrait;
import com.bluescratch.entities.Versement;
@Service//Permet à spring d'intensier les classes de la couche metier
@Transactional//Soit toutes les operations s'executent soit j'annule tout!
public class BanqueMetierImpl  implements IBanqueMetier{
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		
		Compte cpt = compteRepository.getOne(codeCpte);
		
		if(cpt == null)  throw new RuntimeException("Compte introuvable");
		
		return cpt;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		
		Compte cp = consulterCompte(codeCpte);
		Versement v= new Versement(new Date(),montant,cp);
		//On enregistre le versement
		operationRepository.save(v);
		//Mettre à jour le solde
		cp.setSolde(cp.getSolde()+montant);
		//On met à jour le Compte
		compteRepository.save(cp);
		
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		
		Compte cp = consulterCompte(codeCpte);
		
		double facilitesCaisses=0;
		
		//On teste le type de compte 
		if(cp instanceof CompteCourant) 
			facilitesCaisses = ((CompteCourant) cp).getDecouvert();
		
		if(cp.getSolde()+facilitesCaisses<montant)
			throw new RuntimeException("Solde insuffisant");
		
		Retrait r = new Retrait(new Date(),montant,cp);
		//On enregistre le versement
		operationRepository.save(r);
		//Mettre à jour le solde
		cp.setSolde(cp.getSolde()-montant);
		//On met à jour le Compte
		compteRepository.save(cp);
		
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
		
		
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		
		//Pageable p = new PageRequest(page, size, sort);
		
	
		return operationRepository.listOperation(codeCpte,PageRequest.of(page, size) );
	}

}

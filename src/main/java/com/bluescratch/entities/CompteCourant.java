package com.bluescratch.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sun.istack.NotNull;
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
	
	
	private double decouvert;

	public CompteCourant() {
		super();
	}

	public CompteCourant(String codeCompte, Date datecreation, double solde, Client client, double decouvert) {
		super(codeCompte, datecreation, solde, client);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	
	
	
	
	
}


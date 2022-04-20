package com.bluescratch.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE",
discriminatorType = DiscriminatorType.STRING,length = 2)
@Table(name="CPTE")
public abstract class Compte implements Serializable {
	
	 @Id 
	private String codeCompte;
	private Date datecreation;
	private double solde;
	@ManyToOne
	@JoinColumn(name="CODE_CLI")//Clé étrangère par défaut "client"
	private Client client;
	@OneToMany(mappedBy = "comptes",fetch=FetchType.LAZY)
	private Collection<Operation>  operations;
	
	public Compte() {
		super();
	}

	public Compte(String codeCompte, Date datecreation, double solde, Client client) {
		super();
		this.codeCompte = codeCompte;
		this.datecreation = datecreation;
		this.solde = solde;
		this.client = client;
	}

	public String getCodeCompte() {
		return codeCompte;
	}

	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Operation> getOperation() {
		return operations;
	}

	public void setOperation(Collection<Operation> operation) {
		this.operations = operation;
	}
	
	
	
	
	
	
	

}

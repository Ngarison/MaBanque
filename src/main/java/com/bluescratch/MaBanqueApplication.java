package com.bluescratch;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.bluescratch.dao.ClientRepository;
import com.bluescratch.dao.CompteRepository;
import com.bluescratch.dao.OperationRepository;
import com.bluescratch.entities.Client;
import com.bluescratch.entities.Compte;
import com.bluescratch.entities.CompteCourant;
import com.bluescratch.entities.CompteEpargne;
import com.bluescratch.entities.Operation;
import com.bluescratch.entities.Retrait;
import com.bluescratch.entities.Versement;
import com.bluescratch.metier.IBanqueMetier;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner {
	
	//Testes de la couche DAO
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	//Testes de la couche metier
	@Autowired
	private IBanqueMetier banqueMetier;

	public static void main(String[] args)  {
		
	SpringApplication.run(MaBanqueApplication.class, args);
		
	/*ApplicationContext ctx=	SpringApplication.run(MaBanqueApplication.class, args);
	ClientRepository clientRepository= ctx.getBean(ClientRepository.class);
	clientRepository.save(new Client("jules","jules.ngari@gmail.com"));*/
	
	}

	@Override
	public void run(String... args) throws Exception {
	
	Client c1=clientRepository.save(new Client("Hassan","jules.ngari@gmail.com"));
	Client c2=clientRepository.save(new Client("Ayoub","arnaud.ngari@gmail.com"));
	Client c3=clientRepository.save(new Client("Ngarison","test.com"));

	Compte cp1= compteRepository.save(new CompteCourant("c1",new Date(),90000,c1,6000));
	Compte cp2= compteRepository.save(new CompteEpargne("c2",new Date(),50000,c2,3000));
	
	//Pour le compte cp1
	Operation op1= operationRepository.save(new Versement(new Date(),5000,cp1));
	Operation op2= operationRepository.save(new Versement(new Date(),2000,cp1));
	Operation op3= operationRepository.save(new Versement(new Date(),1000,cp1));
	Operation op4= operationRepository.save(new Retrait(new Date(),3000,cp1));
	
	//Pour le compte cp1
	Operation op5= operationRepository.save(new Versement(new Date(),5000,cp2));
	Operation op6= operationRepository.save(new Versement(new Date(),2000,cp2));
	Operation op7= operationRepository.save(new Versement(new Date(),1000,cp2));
	Operation op8= operationRepository.save(new Retrait(new Date(),3000,cp2));
	

	//Couche métier
	banqueMetier.verser("c1", 10000);

	
}

}

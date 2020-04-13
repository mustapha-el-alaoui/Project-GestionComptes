package gestionCompte.app.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import gestionCompte.app.dao.CompteRepository;
import gestionCompte.app.dao.OperationRepository;
import gestionCompte.app.exception.ResourceNotFoundException;
import gestionCompte.app.model.Compte;
import gestionCompte.app.model.Operation;
import gestionCompte.app.model.Retrait;
import gestionCompte.app.model.Versement;

@Service
public class OperationService {
	@Autowired
	private OperationRepository operationrepository;
	@Autowired
	private CompteRepository compterepository;
//verser dans un compte existe.
	@Transactional
	public Compte verser(Long numCompte,double montant) throws ResourceNotFoundException {
		Compte compte=compterepository.findById(numCompte).orElseThrow(
				() -> new ResourceNotFoundException("compte not found for this id :: "+numCompte ));		
		Operation versement =new Versement();
		versement.setDateOperation(new Date());
		versement.setMontant(montant);
		versement.setCompte(compte);
		operationrepository.save(versement);
		compte.setSolde(compte.getSolde()+montant);
		return compte;
	}
//retirer s'un compte existe.
	@Transactional
	public Compte retirer(Long numCompte,double montant) throws ResourceNotFoundException {
		Compte compte=compterepository.findById(numCompte).orElseThrow(
				() -> new ResourceNotFoundException("compte not found for this id :: "+numCompte ));
		if(compte.getSolde()<montant) throw new RuntimeException("solde inssufisant");
		Operation retrait =new Retrait();
		retrait.setDateOperation(new Date());
		retrait.setMontant(montant);
		retrait.setCompte(compte);
		operationrepository.save(retrait);
		compte.setSolde(compte.getSolde()-montant);
		return compte;
	}
//virement de compte a un autre compte.
	@Transactional
	public boolean virement(Long numCompte1,Long numCompte2,double montant) throws ResourceNotFoundException {
		
		retirer(numCompte1, montant);
		verser(numCompte2, montant);
		return true;
	}
//lister les operations.
	@Transactional
	public List<Operation> listoperation(){
		return operationrepository.findAll(PageRequest.of(0,3)).getContent();
	}
}
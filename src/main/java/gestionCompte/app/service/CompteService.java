package gestionCompte.app.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestionCompte.app.dao.CompteRepository;
import gestionCompte.app.model.Compte;

@Service
public class CompteService {
	@Autowired
	private CompteRepository compterepository;
//creer un compte.
	public Compte createCompte(Compte compte) {
		return compterepository.save(compte);
	} 
//lister les comptes exists.
	public Collection<Compte> listCompte(){
		return compterepository.findAll();
	}
//supprimer compte existe.
	public void deleteCompte(Long numCompte) {
		compterepository.deleteById(numCompte);
	}
//rechercher un compte par son id.
	public Optional<Compte> findById(Long numCompte) {
		return compterepository.findById(numCompte);
	}

}
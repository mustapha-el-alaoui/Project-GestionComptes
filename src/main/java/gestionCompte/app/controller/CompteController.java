package gestionCompte.app.controller;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gestionCompte.app.model.Compte;
import gestionCompte.app.model.CompteCourant;
import gestionCompte.app.model.CompteEpargne;
import gestionCompte.app.service.CompteService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CompteController {
	@Autowired
	private CompteService compteservice;

//inserer un compte.
	@RequestMapping(value = "/v1/comptes", method = RequestMethod.POST)
	public Compte ajouter(@RequestBody Compte compte) throws Exception {
		/*Collection<Compte> listecompte = compteservice.listCompte();

		for (Compte c : listecompte) {
			if (compte.getClient().getId().equals(c.getClient().getId())) {
				if (c instanceof CompteCourant && compte instanceof CompteCourant) {
					throw new Exception("compte courant deja exist");
				}
				if (c instanceof CompteEpargne && compte instanceof CompteEpargne) {
					throw new Exception("compte epargne deja exist");
				}
			}
			
		}
		*/
		return compteservice.createCompte(compte);
	}

//lister les comptes exists.
	@RequestMapping(value = "/v1/comptes", method = RequestMethod.GET)
	public Collection<Compte> listComptes() {
		return compteservice.listCompte();
	}

//supprimer un compte existe.
	@RequestMapping(value = "/v1/comptes/{numCompte}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCompte(@PathVariable Long numCompte) {
		if (!compteservice.findById(numCompte).isPresent()) {
			return ResponseEntity.ok("not supprime");
		}

		compteservice.deleteCompte(numCompte);
		return ResponseEntity.ok("supprime");
	}

//rechercher un compte par son id.
	@RequestMapping(value = "/v1/comptes/{numCompte}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Compte>> findById(@PathVariable Long numCompte) throws Exception {
		if (numCompte == null) {

			throw new Exception("compte not exist");
		}
		return ResponseEntity.ok(compteservice.findById(numCompte));
	}

//modifier un compte existe.
	@RequestMapping(value = "/v1/comptes/{numCompte}", method = RequestMethod.PUT)
	public ResponseEntity<Compte> update(@PathVariable Long numCompte, @RequestBody Compte compte) throws Exception {
		Compte compte2 = compteservice.findById(numCompte)
				.orElseThrow(() -> new Exception("Compte not found for this id :: " + numCompte));

		if (compte.getDateCreation() == null)
			compte.setDateCreation(compte2.getDateCreation());
		if (compte instanceof CompteCourant) {
			if (((CompteCourant) compte).getDecouvert() == 0) {
				((CompteCourant) compte).setDecouvert(((CompteCourant) compte2).getDecouvert());
			}
		}
		if (compte instanceof CompteEpargne) {
			if (((CompteEpargne) compte).getTaux() == 0) {
				((CompteEpargne) compte).setTaux(((CompteEpargne) compte2).getTaux());
			}
		}
		compte.setNumCompte(numCompte);
		return ResponseEntity.ok(compteservice.createCompte(compte));
	}
}

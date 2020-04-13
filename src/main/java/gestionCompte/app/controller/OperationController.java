package gestionCompte.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestionCompte.app.exception.ResourceNotFoundException;
import gestionCompte.app.model.Compte;
import gestionCompte.app.model.Operation;
import gestionCompte.app.service.OperationService;


@RestController
public class OperationController {
	@Autowired
	private OperationService operationservice;

//verser dans un compte existe.
	@RequestMapping(value = "/v1/versement", method = RequestMethod.PUT)
	public Compte verser(@RequestParam Long numCompte, @RequestParam double montant) throws ResourceNotFoundException {
		return operationservice.verser(numCompte, montant);
	}

//retirer dans un compte existe.
	@RequestMapping(value = "/v1/retrait", method = RequestMethod.PUT)
	public Compte retirer(@RequestParam Long numCompte, @RequestParam double montant) throws ResourceNotFoundException {
		return operationservice.retirer(numCompte, montant);
	}

//virement d'un compte existe a autre compte existe.
	@RequestMapping(value = "/v1/virement", method = RequestMethod.PUT)
	public boolean virement(@RequestParam Long numCompte1, @RequestParam Long numCompte2, @RequestParam double montant)
			throws ResourceNotFoundException {
		operationservice.virement(numCompte1, numCompte2, montant);
		return true;
	}

//lister les operations.
	@RequestMapping(value = "/v1/get", method = RequestMethod.GET)
	public List<Operation> listoperation() {
		return operationservice.listoperation();
	}
}

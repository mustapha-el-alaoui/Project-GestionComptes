package gestionCompte.app.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gestionCompte.app.model.Client;
import gestionCompte.app.service.ClientService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class ClientController {
	@Autowired
	private ClientService clientservice;

//ajouter un client.
	@RequestMapping(value = "/v1/clients", method = RequestMethod.POST)
	public ResponseEntity<String> createClient(@RequestBody Client client) {
		Collection<Client> listeclient = clientservice.listClient();
		for (Client c : listeclient) {
			if (client.getNom().equals(c.getNom())) {
				return ResponseEntity.ok("error");
			}

		}
		ResponseEntity.ok(clientservice.createClient(client));
		return ResponseEntity.ok("enregistre");
	}

//lister les clients exists.
	@RequestMapping(value = "/v1/clients", method = RequestMethod.GET)
	public Collection<Client> getAllClient() {
		return clientservice.listClient();
	}

//supprimer un client existe.
	@RequestMapping(value = "/v1/clients/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteClient(@PathVariable Long id) {
		if (!clientservice.findById(id).isPresent()) {
			return ResponseEntity.ok("not exist");
		}
		clientservice.deleteClient(id);
		return ResponseEntity.ok("supprime");
	}

//rechercher un client par son id.
	@RequestMapping(value = "/v1/clients/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Client>> getClientById(@PathVariable Long id) {
		return ResponseEntity.ok(clientservice.findById(id));
	}

//modifier un client existe.
	@RequestMapping(value = "/v1/clients/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) throws Exception {
		if (!clientservice.findById(id).isPresent()) {
			throw new Exception("error");
		}

		client.setId(id);
		return ResponseEntity.ok(clientservice.createClient(client));
	}
}

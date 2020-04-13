package gestionCompte.app.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestionCompte.app.dao.ClientRepository;
import gestionCompte.app.model.Client;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientrepository;

	// ajouter un client.
	public Client createClient(Client client) {
		return clientrepository.save(client);
	}

// lister les clients exists.
	public Collection<Client> listClient() {
		return clientrepository.findAll();
	}

// supprimer un client exist.
	public void deleteClient(Long id) {
		clientrepository.deleteById(id);
	}

// recherche d'un client par son id.
	public Optional<Client> findById(Long id) {
		return clientrepository.findById(id);
	}

}
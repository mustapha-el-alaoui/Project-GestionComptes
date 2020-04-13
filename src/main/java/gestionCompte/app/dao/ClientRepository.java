package gestionCompte.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestionCompte.app.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
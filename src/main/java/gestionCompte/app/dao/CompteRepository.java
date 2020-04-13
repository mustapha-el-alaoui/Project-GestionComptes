package gestionCompte.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestionCompte.app.model.Compte;


@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

}
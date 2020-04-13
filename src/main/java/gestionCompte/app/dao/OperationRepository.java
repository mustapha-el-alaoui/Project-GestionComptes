package gestionCompte.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestionCompte.app.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
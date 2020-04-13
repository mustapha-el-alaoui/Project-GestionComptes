package gestionCompte.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(name = "verement", value = Versement.class), @Type(name = "retrait", value = Retrait.class) })
public abstract class Operation {

	@Id
	@GeneratedValue
	private Long numOperation;
	@CreationTimestamp
	private Date dateOperation;
	private double montant;
	@ManyToOne
	@JoinColumn(name = "compte_numcompte", referencedColumnName = "numCompte")
	private Compte compte;

	public Long getNumOperation() {
		return numOperation;
	}

	public void setNumOperation(Long numOperation) {
		this.numOperation = numOperation;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
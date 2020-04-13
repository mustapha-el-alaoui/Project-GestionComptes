package gestionCompte.app.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeCompte",discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(name="cc",value =CompteCourant.class),
	@Type(name ="ce",value =CompteEpargne.class)
})
public abstract class Compte  {
	
	@Id
	private Long numCompte;
	@CreationTimestamp
	private Date dateCreation;
	private double solde;
	@ManyToOne
   @JoinColumn(name="client_code", referencedColumnName="id") 
	private Client client;
	@OneToMany(mappedBy = "compte",fetch = FetchType.LAZY)
	private Collection<Operation> operation;
	public Long getNumCompte() {
		return numCompte;
	}
	public void setNumCompte(Long numCompte) {
		this.numCompte = numCompte;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	@JsonIgnore
	public Collection<Operation> getOperation() {
		return operation;
	}
	public void setOperation(Collection<Operation> operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Compte [numCompte=" + numCompte + ", dateCreation=" + dateCreation + ", solde=" + solde + ", client="
				+ client + "]";
	}


	

}
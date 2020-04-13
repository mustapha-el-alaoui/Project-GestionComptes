package gestionCompte.app.model;

import javax.persistence.Entity;

@Entity
public class CompteCourant extends Compte {

	private double decouvert;

	public CompteCourant() {
		super();
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

}
package gestionCompte.app.model;

import javax.persistence.Entity;

@Entity
public class CompteEpargne extends Compte {

	private double taux;

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

}

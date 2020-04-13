package gestionCompte.app.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client {
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String profileImage;
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Collection<Compte> compte;

	public Client() {
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public Collection<Compte> getComptes() {
		return compte;
	}

	public void setComptes(Collection<Compte> compte) {
		this.compte = compte;
	}

}
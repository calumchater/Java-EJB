package ejb.entites;

import java.io.Serializable;

import javax.persistence.InheritanceType;

@javax.persistence.Entity 
@javax.persistence.Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

abstract public class Personne implements Serializable
{

	private static final long serialVersionUID = 1L;



	@javax.persistence.Id
	@javax.persistence.Column(nullable = false) 
	protected String email;

	

	protected String nom;


	
	public Personne(){
		super();
	}

	
	public String getEmail() {
		return this.email;
	}

	
	public String getNom() {
		return this.nom;
	}

	
	public void setEmail(String myEmail) {
		this.email = myEmail;
	}

	
	public void setNom(String myNom) {
		this.nom = myNom;
	}

	
	public void unsetEmail() {
		this.email = "";
	}

	
	public void unsetNom() {
		this.nom = "";
	}

}


package ejb.entites;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.FetchType;

 
@javax.persistence.Entity 
public class Reunion implements Serializable
{


	private static final long serialVersionUID = 1L;


	public enum Etat {CREATION,INSCRIPTION,CLOTURE};
	 
	
	@javax.persistence.Id 
	@javax.persistence.Column(nullable = false) 
	protected String titre;

	protected String description;
 

	protected String email;


	protected Etat etat ;

	@javax.persistence.ManyToMany(fetch=FetchType.EAGER)
	protected Set<Participant> participants;
	
	public Set<Participant> getParticipants() {
		return participants;
	}

	public Set<Creneau> getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(Set<Creneau> creneaux) {
		this.creneaux = creneaux;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	@javax.persistence.ManyToOne
	protected Initiateur personne;
 
	@javax.persistence.OneToMany(fetch = FetchType.EAGER) 
	protected Set<Creneau> creneaux;
	
	public Reunion(){
		super();
	}

	public void basicSetPersonne(Initiateur myPersonne) {
		if (this.personne != myPersonne) {
			if (myPersonne != null){
				if (this.personne != myPersonne) {
					Initiateur oldpersonne = this.personne;
					this.personne = myPersonne;
					if (oldpersonne != null)
						oldpersonne.removeReunion(this);
				}
			}
		}
	}

	 
	

	 
	public String getTitre() {
		return this.titre;
	}

	 
	public String getDescription() {
		return this.description;
	}

	 
	public String getEmail() {
		return this.email;
	}

	 
	public Etat getEtat() {
		return this.etat;
	}

	
	public Initiateur getPersonne() {
		return this.personne;
	}

	
	public void setTitre(String myTitre) {
		this.titre = myTitre;
	}

	 
	public void setDescription(String myDescription) {
		this.description = myDescription;
	}

	 
	public void setEmail(String myEmail) {
		this.email = myEmail;
	}

	 
	public void setEtat(Etat inscription) {
		this.etat = inscription;
	}

	 
	public void setPersonne(Initiateur myPersonne) {
		this.basicSetPersonne(myPersonne);
		myPersonne.addReunion(this);
	}


	
	public void addParticipant(Participant part) {
		this.participants.add(part);
	}
	 

	 
	public void unsetTitre() {
		this.titre = "";
	}

	 
	public void unsetDescription() {
		this.description = "";
	}

	 
	public void unsetEmail() {
		this.email = "";
	}

	 
	public void unsetEtat() {
		this.etat = null;
	}

	 
	public void unsetPersonne() {
		if (this.personne == null)
			return;
		Initiateur oldpersonne = this.personne;
		this.personne = null;
		oldpersonne.removeReunion(this);
	}



}


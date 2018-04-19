package ejb.entites;

import java.io.Serializable;

import javax.persistence.GeneratedValue;

@javax.persistence.Entity 
public class Disponibilite implements Serializable
{


	private static final long serialVersionUID = 1352255171937418957L;


	@javax.persistence.Id
	@GeneratedValue
	@javax.persistence.Column(nullable = false) 
	protected int idDispo;

	 
	@javax.persistence.Column
	protected boolean disponible = false;

	 
	@javax.persistence.ManyToOne 
	@javax.persistence.JoinColumn
	protected Creneau creneau;
	
	@javax.persistence.ManyToOne 
	protected Participant participant;

	

	public Disponibilite(){
		super();
	}

	
	public int getIdDispo() {
		return this.idDispo;
	}

	
	public boolean isDisponible() {
		return this.disponible;
	}

	
	public Creneau getCreneau() {
		return this.creneau;
	}
	
	public boolean getDisponible() {
		return this.disponible;
	}

	
	public void setDisponible(boolean myDisponible) {
		this.disponible = myDisponible;
	}
	
	public void setCreneau(Creneau cre){
		this.creneau = cre;
	}
	
	public void setParticipant(Personne part){
		this.participant = (Participant) part;
	}
	
	public Participant getParticipant(){
		return this.participant;
	}

}


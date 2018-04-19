
package ejb.entites;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

import javax.persistence.FetchType;

@javax.persistence.Entity 
public class Creneau implements Serializable
{

	private static final long serialVersionUID = 1L;

	@javax.persistence.Id 
	@javax.persistence.Column(nullable = false)
	protected String timestamp_reu;

	protected Timestamp timestamp ;


	protected String jour;

	
	 
 
	protected String heure;


	 
	@javax.persistence.ManyToOne
	protected Reunion reunion;


	 
	@javax.persistence.OneToMany(fetch=FetchType.EAGER)
	protected Set<Disponibilite> disponibilite;



	public Creneau(){
		super();
	}

	public String getTimestamp_reu() {
		return timestamp_reu;
	}


	public void setTimestamp_reu(String timestamp_reu) {
		this.timestamp_reu = timestamp_reu;
	}

	
	
	public String getJour() {
		return this.jour;
	}

	
	public String getHeure() {
		return this.heure;
	}

	
	public Reunion getReunion() {
		
		return this.reunion;
	}

	
	public Set<Disponibilite> getDisponibilite() {
		if(this.disponibilite == null) {
				this.disponibilite = new HashSet<Disponibilite>();
		}
		return (Set<Disponibilite>) this.disponibilite;
	}

	
	 
	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}


	public void setDisponibilite(Set<Disponibilite> disponibilite) {
		this.disponibilite = disponibilite;
	}


	public void addAllDisponibilite(Set<Disponibilite> newDisponibilite) {
		if (this.disponibilite == null) {
			this.disponibilite = new HashSet<Disponibilite>();
		}
		for (Disponibilite tmp : newDisponibilite)
			tmp.setCreneau(this);
		
	}


	 
	public void removeAllDisponibilite(Set<Disponibilite> newDisponibilite) {
		if(this.disponibilite == null) {
			return;
		}
		
		this.disponibilite.removeAll(newDisponibilite);
	}

	 
	public void setJour(String myJour) {
		this.jour = myJour;
	}

	 
	public void setHeure(String myHeure) {
		this.heure = myHeure;
	}

	 
	

	 
	public void addDisponibilite(Disponibilite newDisponibilite) {
		if(this.disponibilite == null) {
			this.disponibilite = new HashSet<Disponibilite>();
		}
		
		if (this.disponibilite.add(newDisponibilite))
			newDisponibilite.setCreneau(this);
	}

	 
	public void unsetJour() {
		this.jour = "";
	}

	 
	public void unsetHeure() {
		this.heure = "";
	}


}


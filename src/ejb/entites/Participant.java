package ejb.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.Transient;

@javax.persistence.Entity 

public class Participant extends Personne implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@OneToMany
	@Transient
	protected Set<Disponibilite> dispos = new HashSet<Disponibilite>();
	
	public void setDispos(Set<Disponibilite> dispos) {
		this.dispos = dispos;
	}

	@javax.persistence.ManyToMany
	protected Set<Reunion> reunions = new HashSet<Reunion>();
	
	public Set<Reunion> getReunions() {
		return reunions;
	}

	public void setReunions(Set<Reunion> reunions) {
		this.reunions = reunions;
	}

	public Set<Disponibilite> getDispos() {
		return dispos;
	}

}

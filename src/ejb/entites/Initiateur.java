package ejb.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@javax.persistence.Entity 
public class Initiateur extends Personne implements Serializable
{
	 
	 
	private static final long serialVersionUID = 1L;
	@javax.persistence.OneToMany(mappedBy = "personne") 
	protected Set<Reunion> reunion;

	
	public Initiateur(){
		super();
	}

	 
	public Set<Reunion> getReunion() {
		if(this.reunion == null) {
				this.reunion = new HashSet<Reunion>();
		}
		return (Set<Reunion>) this.reunion;
	}

	 
	public void addAllReunion(Set<Reunion> newReunion) {
		if (this.reunion == null) {
			this.reunion = new HashSet<Reunion>();
		}
		for (Reunion tmp : newReunion)
			tmp.setPersonne(this);
		
	}

	 
	public void removeAllReunion(Set<Reunion> newReunion) {
		if(this.reunion == null) {
			return;
		}
		
		this.reunion.removeAll(newReunion);
	}

	 
	public void addReunion(Reunion newReunion) {
		if(this.reunion == null) {
			this.reunion = new HashSet<Reunion>();
		}
		
		if (this.reunion.add(newReunion))
			newReunion.basicSetPersonne(this);
	}

	 
	public void removeReunion(Reunion oldReunion) {
		if(this.reunion == null)
			return;
		
		if (this.reunion.remove(oldReunion))
			oldReunion.unsetPersonne();
		
	}

}


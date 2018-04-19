package ejb.sessions;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.entites.Participant;
import ejb.entites.Reunion;

@Stateless
public class ServiceBean implements ServiceLocal, ServiceRemote {


	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="unit-doudeule")
	protected EntityManager em;

		
	@Override
	public Reunion recupererReunion(String titre) throws ReunionInconnueException{
		 
		Reunion reu = em.find(Reunion.class, titre);
		if (reu==null)throw new ReunionInconnueException();
		return reu;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Reunion> listeReunion() {
		return (Collection<Reunion>) em.createQuery("from Reunion r").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Participant> listeParticipants() {
		return (Collection<Participant>) em.createQuery("from Participant p").getResultList();
	}	
}

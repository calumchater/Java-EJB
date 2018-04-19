package ejb.sessions;

import java.io.Serializable;

import javax.ejb.Stateless;

import ejb.entites.Creneau ;
import ejb.entites.Initiateur;
import ejb.entites.Reunion ;
import ejb.entites.Reunion.Etat;

@Stateless
public class ServiceInitBean extends ServiceBean implements ServiceInitRemote, Serializable {

	
	
	private static final long serialVersionUID = 1L;

	@Override
	public void creerReunion(String titre, String description, String email)
			throws ReunionExistanteException {
		try{
			recupererReunion(titre);
			throw new ReunionExistanteException();
		} catch (ReunionInconnueException e) {
			Reunion reu = new Reunion();
			Initiateur initiateur = new Initiateur();
			initiateur.setEmail(email);
			reu.setTitre(titre);
			reu.setDescription(description);
			reu.setEmail(email);
			reu.setEtat(Etat.CREATION);
			reu.setPersonne(initiateur);
			em.persist(initiateur);
			em.persist(reu);
		}
	}
	
	@Override	
	public void ajoutCreneauReunion(String titre, String jour, String heure) throws ReunionInconnueException, CreneauExistantException, EtatIncompatibleException {
		Reunion reu = em.find(Reunion.class, titre);
		if (reu==null)throw new ReunionInconnueException();
		if(reu.getEtat() == Etat.CLOTURE) throw new EtatIncompatibleException();
		String timestamp_reu = jour +"_" + heure + "_" + titre;
		if(em.find(Creneau.class, timestamp_reu) != null) throw new CreneauExistantException();
		Creneau creneau = new Creneau();
		creneau.setTimestamp_reu(jour + "_" + heure +"_"+ titre);
		creneau.setTimestamp(java.sql.Timestamp.valueOf(jour + " " + heure));
		reu.getCreneaux().add(creneau);
		em.persist(reu);
		em.persist(creneau);
	} 
		
	
	@Override
		public void activInscriptionReunion(String titre)
			throws EtatIncompatibleException, ReunionInconnueException{
		Reunion reu = em.find(Reunion.class, titre);
		if (reu==null)throw new ReunionInconnueException();
		if(reu.getEtat()!= Etat.CREATION) throw new EtatIncompatibleException();
		reu.setEtat(Etat.INSCRIPTION);
		em.persist(reu);
	}
		
	@Override
	public void clotureInscriptionReunion(String titre)
			throws EtatIncompatibleException, ReunionInconnueException{
		Reunion reu = em.find(Reunion.class, titre);
		if (reu==null)throw new ReunionInconnueException();
		if(reu.getEtat()!= Etat.INSCRIPTION) throw new EtatIncompatibleException();
		reu.setEtat(Etat.CLOTURE);
		em.persist(reu);
	}

	
	
}

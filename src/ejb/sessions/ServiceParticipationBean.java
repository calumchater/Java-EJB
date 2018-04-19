package ejb.sessions;

import java.io.Serializable;

import javax.ejb.Stateless;

import ejb.entites.Creneau;
import ejb.entites.Disponibilite;
import ejb.entites.Participant;
import ejb.entites.Personne;
import ejb.entites.Reunion;
import ejb.entites.Reunion.Etat;

@Stateless
public class ServiceParticipationBean extends ServiceBean implements ServiceParticipationLocal,ServiceParticipationRemote, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void indicationDisponibilite(String mail, String jour, String heure, String titre) throws PersonneInexistanteException, CreneauInexistantException, EtatIncompatibleException, ReunionInconnueException {
		
		Participant part = (Participant) em.find(Personne.class, mail);
		if(part==null) throw new PersonneInexistanteException();
		
		String time_reu = jour + "_"+ heure + "_" + titre;
		Creneau creneau = em.find(Creneau.class, time_reu);
		if(creneau==null) throw new CreneauInexistantException();
		
		Reunion reu = em.find(Reunion.class, titre);
		if(reu == null) throw new ReunionInconnueException();
		if(reu.getEtat() != Etat.INSCRIPTION) throw new EtatIncompatibleException();
		
		reu.addParticipant(part);
		Disponibilite dispo = new Disponibilite();
		dispo.setCreneau(creneau);

		dispo.setParticipant(part);	
		dispo.setDisponible(true);

		part.getDispos().add(dispo);

		creneau.getDisponibilite().add(dispo);

		em.persist(creneau);
		em.persist(part);
		em.persist(dispo);
	}
	
	@Override
	public void enregistrerParticipant(String email, String nom) throws PersonneExistanteException{
		
		if(em.find(Personne.class, email) != null) throw new PersonneExistanteException();
		Participant part = new Participant();
		part.setEmail(email);
		part.setNom(nom);
		em.persist(part);
		}
	
	public boolean getDispoCreneau(String timestamp_reu, String email) throws CreneauInexistantException{
		Creneau cr = (Creneau) em.find(Creneau.class, timestamp_reu);
		if(cr == null) throw new CreneauInexistantException();
		boolean testDispo = false;
		for(Disponibilite d : cr.getDisponibilite()){
			if(d.getParticipant().getEmail() == email){
				if(d.getDisponible()==true){
					testDispo = true;
				}
			}
		}
		return testDispo;
	}
		
		
	}

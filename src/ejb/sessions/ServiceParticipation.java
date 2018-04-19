package ejb.sessions;

import java.io.Serializable;

public interface ServiceParticipation extends Service, Serializable  {

	public void indicationDisponibilite(String part, String jour, String heure, String titre) throws ReunionInconnueException,PersonneInexistanteException, CreneauInexistantException, EtatIncompatibleException;
	
	public void enregistrerParticipant(String email, String nom) throws PersonneExistanteException;

	public boolean getDispoCreneau(String timestamp_reu, String email) throws CreneauInexistantException;
}

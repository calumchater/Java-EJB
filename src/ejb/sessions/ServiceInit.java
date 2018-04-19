package ejb.sessions;

import java.io.Serializable;

public interface ServiceInit extends Service, Serializable {

	public void creerReunion(String titre, String description, String email)
			throws ReunionExistanteException;
	
	public void ajoutCreneauReunion(String titre, String jour, String heure) throws ReunionInconnueException,CreneauExistantException,EtatIncompatibleException ; 
	
	public void activInscriptionReunion(String titre)
			throws EtatIncompatibleException, ReunionInconnueException;
	
	public void clotureInscriptionReunion(String titre)
			throws  EtatIncompatibleException,ReunionInconnueException;

}

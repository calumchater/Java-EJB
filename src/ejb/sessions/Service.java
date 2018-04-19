package ejb.sessions;

import java.io.Serializable;
import java.util.Collection;

import ejb.entites.Participant;
import ejb.entites.Reunion;

public interface Service extends Serializable {
		

		public Reunion recupererReunion(String titre) throws ReunionInconnueException;

		public Collection<Reunion> listeReunion();
		
		public Collection<Participant> listeParticipants();

		
	}


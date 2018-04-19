package client ;


import java.io.IOException;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.entites.Creneau;
import ejb.entites.Disponibilite;
import ejb.entites.Participant;
import ejb.entites.Reunion;
import ejb.sessions.CreneauExistantException;
import ejb.sessions.CreneauInexistantException;
import ejb.sessions.EtatIncompatibleException;
import ejb.sessions.PersonneExistanteException;
import ejb.sessions.PersonneInexistanteException;
import ejb.sessions.ReunionExistanteException;
import ejb.sessions.ReunionInconnueException;
import ejb.sessions.Service;
import ejb.sessions.ServiceInit;
import ejb.sessions.ServiceParticipation;

public class Main {
	
	
 public static void main(String[] args) throws NamingException,EtatIncompatibleException, ReunionExistanteException, ReunionInconnueException, PersonneExistanteException, PersonneInexistanteException, CreneauInexistantException, IOException, CreneauExistantException { 
 
	 try {
		 
		 ///////////////////////////
		 // Creation des services //
		 ///////////////////////////
		 

		 // service initiateur
	      InitialContext ctx1 = new InitialContext();
	      Object obj1 = ctx1.lookup("ejb:doudeule/doudeuleSessions/"+
    		  "ServiceInitBean!ejb.sessions.ServiceInitRemote");
	      ServiceInit serviceInit = (ServiceInit) obj1;
	      
	      // service participation
	      InitialContext ctx2 = new InitialContext();
	      Object obj2 = ctx2.lookup("ejb:doudeule/doudeuleSessions/"+
    		  "ServiceParticipationBean!ejb.sessions.ServiceParticipationRemote");
	      ServiceParticipation serviceParticipation = (ServiceParticipation) obj2;
	      
	      // service
	      InitialContext ctx3 = new InitialContext();
	      Object obj3 = ctx3.lookup("ejb:doudeule/doudeuleSessions/"+
	      	  "ServiceBean!ejb.sessions.ServiceRemote");
	      Service service= (Service) obj3;
	      
	      ///////////////////
	      // Test méthodes //
	      ///////////////////
	     
	    
	      // Test Creation Reunion
	      serviceInit.creerReunion("AL Team", "coordination projet AL", "Olivier.Caron@polytech-lille.fr");
	      
	      //Test ajout creneau Reunion
	      serviceInit.ajoutCreneauReunion("AL Team", "2018-03-21", "18:15:00.0");

	      serviceInit.ajoutCreneauReunion("AL Team", "2018-03-22", "16:15:00.0");

	      serviceInit.ajoutCreneauReunion("AL Team", "2018-03-22", "18:15:00.0");

	      serviceInit.ajoutCreneauReunion("AL Team", "2018-03-23", "18:15:00.0");
	     
	      // Test activer inscription
	      serviceInit.activInscriptionReunion("AL Team");
	      
	      // Test enregistrer participant
	      serviceParticipation.enregistrerParticipant("Olivier.caron@polytech-lille.fr", "Olivier");

	      serviceParticipation.enregistrerParticipant("Walter@polytech-lille.fr", "Walter");

	      serviceParticipation.enregistrerParticipant("Bernard@polytech-lille.fr", "Bernard");
	      
	      // Test indiquer disponibilite
	      serviceParticipation.indicationDisponibilite("Bernard@polytech-lille.fr","2018-03-21", "18:15:00.0", "AL Team");
	      serviceParticipation.indicationDisponibilite("Bernard@polytech-lille.fr","2018-03-22", "16:15:00.0", "AL Team");

	      serviceParticipation.indicationDisponibilite("Olivier.caron@polytech-lille.fr","2018-03-21", "18:15:00.0", "AL Team");
	      serviceParticipation.indicationDisponibilite("Olivier.caron@polytech-lille.fr","2018-03-22", "16:15:00.0", "AL Team");
	      serviceParticipation.indicationDisponibilite("Olivier.caron@polytech-lille.fr","2018-03-22", "18:15:00.0", "AL Team");

	      serviceParticipation.indicationDisponibilite("Walter@polytech-lille.fr","2018-03-22", "18:15:00.0", "AL Team");
	      serviceParticipation.indicationDisponibilite("Walter@polytech-lille.fr","2018-03-22", "18:15:00.0", "AL Team");

	      // Test cloture inscription
	      serviceInit.clotureInscriptionReunion("AL Team");
	      
	      // Test recuperer reunion
	      Reunion reuAffichage = serviceParticipation.recupererReunion("AL Team");
		
	      // Test liste reunions
	      System.out.println("Voici la liste des titres des reunions:");
	      Collection<Reunion> reunions = service.listeReunion();
	      for(Reunion r : reunions){
	    	  System.out.println(r.getTitre());
	      }
	      
	      
	      // Partie affichage
	      
	      for(Creneau cr : reuAffichage.getCreneaux()){
		      System.out.println("----------------------------");
		      System.out.println(cr.getTimestamp());
		      boolean testCr = false;
		      for(Participant p : reuAffichage.getParticipants()){
		    	  testCr = false;
		    	  for(Disponibilite d : cr.getDisponibilite()){
		    		  if(d.getParticipant() == p){
		    			  testCr = true;
		    		  }
		    	  }
	    		  if(testCr){
	    		      System.out.println(p.getNom() +"  OK ");
	    		  }
	    		  else{
	    		      System.out.println(p.getNom() +"  bad ");
	    		  }
		      }
	      } 
		      
	 	}
	 	
 	catch(NamingException ex) {	
 		System.out.print("NamingException");
 		}
	 
	catch(ReunionExistanteException ex) {			
	 		System.out.print("ReunionExistanteException");
	 	}
	 catch(ReunionInconnueException ex){
		 System.out.print("ReunionInconnueException");
	 }
	 catch(PersonneExistanteException ex){
		 System.out.print("PersonneExistanteException");
	 }
	 catch(PersonneInexistanteException ex){
		 System.out.print("PersonneInexistanteException,");
	 }
	 catch(CreneauInexistantException ex){
		 System.out.print("CreneauInexistantException");
	 } 
	 catch (EtatIncompatibleException e) {
		System.out.print("Etat incompatible exception");
	 }
	 catch (CreneauExistantException e) {
		System.out.print("Creneau Existant exception");
	 }

}	
 
}


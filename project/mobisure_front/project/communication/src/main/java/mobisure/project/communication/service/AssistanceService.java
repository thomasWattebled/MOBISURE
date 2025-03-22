package mobisure.project.communication.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;

public interface AssistanceService {

	public void createAssistance(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone);
	
	public void createDepannage(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone, String ville, String rue);
	
	public void createAccident(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone, String ville, String rue, int nbBlesse);
	
	public void createRemboursement(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone,float montant,String motif);
	
	public void createMedical(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone,String motif);
	
	public List<Assistance> getAllAssistance();
	
	public void updateAssistance(String id,Status status);
	
	public List<Assistance> getMyAssistance(Long id);
	
	public Assistance getByNumDossier(String numDossier);

	public List<Assistance> getAssistanceDisponnible();
}

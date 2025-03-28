package mobisure.project.communication.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;
import mobisure.project.communication.entity.type.Accident;
import mobisure.project.communication.entity.type.Depannage;
import mobisure.project.communication.entity.type.Medical;
import mobisure.project.communication.entity.type.Remboursement;
import mobisure.project.communication.repository.AssistanceRepository;

@Service
public class AssistanceServiceImpl implements AssistanceService {

	@Autowired
	private AssistanceRepository repo;

	@Override
	public List<Assistance> getAllAssistance() {
		return repo.findAll();
	}
	
	public void updateAssistance(String id,Status status){
		
		Optional<Assistance> getAssistance = repo.findById(id);
		
		if(getAssistance.isPresent()) {
			Assistance assistance = getAssistance.get();
			assistance.setStatus(status);
			repo.save(assistance);
		}
		
	}

	@Override
	public List<Assistance> getMyAssistance(Long id) {
		return repo.findByIdClient(id);
	}

	@Override
	public Assistance getByNumDossier(String numDossier) {
		return repo.findByNumDossier(numDossier);
	}

	@Override
	public List<Assistance> getAssistanceDisponnible() {
		return repo.findByGererFalse();
	}
	
	@Override
	public void createAssistance(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone) {
		Assistance assistance = new Assistance(id_client,status,date,message,type,nom,prenom,mail,mdp,telephone);
		repo.save(assistance);
	}

	@Override
	public void createDepannage(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone, String ville, String rue) {
		
		repo.save(new Depannage(id_client,status,date,message,type,nom,prenom,mail,mdp,telephone,ville,rue));
		
	}

	@Override
	public void createAccident(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone, String ville, String rue,
			int nbBlesse) {
		
		repo.save(new Accident(id_client,status,date,message,type,nom,prenom,mail,mdp,telephone,ville,rue,nbBlesse));
		
	}

	@Override
	public void createRemboursement(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone, float montant, String motif) {
		
		repo.save(new Remboursement(id_client,status,date,message,type,nom,prenom,mail,mdp,telephone,montant,motif));
		
	}

	@Override
	public void createMedical(Long id_client, Status status, Date date, String message, TypeAssistance type, String nom,
			String prenom, String mail, String mdp, String telephone, String motif) {
		
		repo.save(new Medical(id_client,status,date,message,type,nom,prenom,mail,mdp,telephone,motif));
		
	}
	
}

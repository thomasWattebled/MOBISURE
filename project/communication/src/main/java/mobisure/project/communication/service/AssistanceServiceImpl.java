package mobisure.project.communication.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;
import mobisure.project.communication.repository.AssistanceRepository;

@Service
public class AssistanceServiceImpl implements AssistanceService {

	@Autowired
	private AssistanceRepository repo;

	@Override
	public void createAssistance(Long id_client, Status status, Date date, String message, TypeAssistance type,
			String nom, String prenom, String mail, String mdp, String telephone) {
		Assistance assistance = new Assistance(id_client,status,date,message,type,nom,prenom,mail,mdp,telephone);
		repo.save(assistance);
	}

	@Override
	public List<Assistance> getAllAssistance() {
		return repo.findAll();
	}
	
	public void updateAssistance(Long id,Status status){
		
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
	
}

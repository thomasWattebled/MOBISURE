package mobisure.project.communication.service;

import java.util.Date;
import java.util.List;

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
	public void createAssistance(Long id_client, Status status, Date date, String message, TypeAssistance type) {
		Assistance assistance = new Assistance(id_client,status,date,message,type);
		repo.save(assistance);
	}

	@Override
	public List<Assistance> getAllAssistance() {
		return repo.findAll();
	}
	
	
	
}

package mobisure.project.communication.service;

import java.util.Date;
import java.util.List;

import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;

public interface AssistanceService {

	public void createAssistance(Long id_client, Status status, Date date, String message, TypeAssistance type);
	
	public List<Assistance> getAllAssistance();
	
}

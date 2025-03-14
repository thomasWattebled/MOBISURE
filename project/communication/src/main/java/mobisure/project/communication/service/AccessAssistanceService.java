package mobisure.project.communication.service;

import java.util.List;

import mobisure.project.communication.entity.AccessAssistance;
import mobisure.project.communication.entity.Assistance;

public interface AccessAssistanceService {

	public boolean getPermissionOfUser(String idAssistance,Long idUser);
	
	public void addAccess(String idAssistance,Long idUser);

	public List<AccessAssistance> getAll();

	public List<Assistance> getMyFolder(Long idUser);
}

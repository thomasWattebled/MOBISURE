package mobisure.project.communication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.communication.entity.AccessAssistance;
import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.repository.AccessAssistanceRepository;
import mobisure.project.communication.repository.AssistanceRepository;

@Service
public class AccessAssistanceServiceImpl implements AccessAssistanceService {

	@Autowired
	private AccessAssistanceRepository repo;
	
	@Autowired
	private AssistanceRepository repoAssistance;
	
	@Override
	public boolean getPermissionOfUser(String idAssistance, Long idUser) {
	    AccessAssistance resultat = repo.findByIdAssistanceAndIdUser(idAssistance, idUser);
	    if (resultat != null) {
	        return false;
	    }
	    return true;
	}


	@Override
	public void addAccess(String idAssistance, Long idUser) {
		AccessAssistance newAccess = new AccessAssistance(idAssistance,idUser);
		Assistance assistance = repoAssistance.findByNumDossier(idAssistance); 
		assistance.setGerer(true);
		repoAssistance.save(assistance);
		repo.save(newAccess);
	}

	@Override
	public List<AccessAssistance> getAll() {
		return repo.findAll();
	}

	@Override
	public List<Assistance> getMyFolder(Long idUser) {
		List<AccessAssistance> access = repo.findByIdUser(idUser);
		System.out.println(access);
		List<Assistance> assistance = new ArrayList<>();
		access.forEach( a -> {
			assistance.add(repoAssistance.findByNumDossier(a.getIdAssistance()));
		});
		return assistance;
	}

	@Override
	public void removeAccess(String idAssistance, Long idUser) {
		AccessAssistance access = repo.findByIdAssistanceAndIdUser(idAssistance, idUser);
		Assistance assistance = repoAssistance.findByNumDossier(access.getIdAssistance());
		
		if(assistance.isGerer()) { 
			assistance.setGerer(false);
			repoAssistance.save(assistance);
		}
		
	    repo.delete(access);
	}

}

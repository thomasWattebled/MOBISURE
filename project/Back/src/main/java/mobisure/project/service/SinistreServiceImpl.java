package mobisure.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.dto.SinistreDto;
import mobisure.project.entity.Sinistre;
import mobisure.project.repository.SinistreRepository;

@Service
public class SinistreServiceImpl implements SinistreService {
	
	@Autowired
	SinistreRepository sinistreRepo;
	
	@Override
	public Sinistre convertToEntity (SinistreDto sinistreDto) {
		return new Sinistre(sinistreDto.getContractId(), sinistreDto.getUserId(), sinistreDto.getCategory(), sinistreDto.getDate());
	}
	
	@Override
	public SinistreDto convertToDto (Sinistre sinistre) {
		SinistreDto sinistreDto = new SinistreDto(sinistre.getContractId(), sinistre.getUserId(), sinistre.getCategory(), sinistre.getDate());
		sinistreDto.setId(sinistre.getId());
		return sinistreDto;
	}
	
	@Override
	public SinistreDto getSinistreById (Long id) {
		Optional<Sinistre> sinistre = sinistreRepo.findById(id);
		return (sinistre.isPresent() ? this.convertToDto(sinistre.get()) : null);
	}
	
	@Override
	public List<SinistreDto> getSinitreByUserId (Long userId) {
		List<Sinistre> sinistres = this.sinistreRepo.findByUserId(userId);
		List<SinistreDto> sinistresDto = new ArrayList<SinistreDto>();
		for (Sinistre sinistre : sinistres) {
			sinistresDto.add(this.convertToDto(sinistre));
		}
		return sinistresDto;
	}
	
	@Override
	public List<SinistreDto> getSinitreByContractId (Long contractId) {
		List<Sinistre> sinistres = this.sinistreRepo.findByContractId(contractId);
		List<SinistreDto> sinistresDto = new ArrayList<SinistreDto>();
		for (Sinistre sinistre : sinistres) {
			sinistresDto.add(this.convertToDto(sinistre));
		}
		return sinistresDto;
 	}
	
	@Override
	public Sinistre saveSinistre (SinistreDto sinistreDto) {
		return this.sinistreRepo.save(this.convertToEntity(sinistreDto));
	}
	
	@Override
	public void delete (Long id) {
		this.sinistreRepo.deleteById(id);
	}
}

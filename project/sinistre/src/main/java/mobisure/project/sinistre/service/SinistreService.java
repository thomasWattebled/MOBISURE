package mobisure.project.sinistre.service;

import java.util.List;
import java.util.Optional;

import mobisure.project.sinistre.dto.SinistreDto;
import mobisure.project.sinistre.entity.Sinistre;

public interface SinistreService {

	public Sinistre convertToEntity(SinistreDto sinistreDto);

	public SinistreDto convertToDto(Sinistre sinistre);

	public SinistreDto getSinistreById(Long id);

	public List<SinistreDto> getSinitreByUserId(Long userId);

	public List<SinistreDto> getSinitreByContractId(Long contractId);

	public Sinistre saveSinistre(SinistreDto sinistreDto);

	public void delete(Long id);
}

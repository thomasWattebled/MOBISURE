package mobisure.project.sinistre.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mobisure.project.sinistre.document.CarDocument;
import mobisure.project.sinistre.document.FileDocument;
import mobisure.project.sinistre.document.HealthDocument;
import mobisure.project.sinistre.dto.SinistreCarDto;
import mobisure.project.sinistre.dto.SinistreDto;
import mobisure.project.sinistre.dto.SinistreHealthDto;
import mobisure.project.sinistre.entity.Sinistre;
import mobisure.project.sinistre.service.CarService;
import mobisure.project.sinistre.service.FileService;
import mobisure.project.sinistre.service.HealthService;
import mobisure.project.sinistre.service.SinistreService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SinistreController {

	@Autowired
	private SinistreService sinistreService;

	@Autowired
	private FileService fileService;

	@Autowired
	private CarService carService;

	@Autowired
	private HealthService healthService;

	@GetMapping("sinistres/contractId/{contractId}")
	public List<SinistreDto> getSinistresByContractId(@PathVariable Long contractId) {
		return sinistreService.getSinitreByContractId(contractId);
	}

	@GetMapping("sinistres/userId/{userId}")
	public List<SinistreDto> getSinistresByUserId(@PathVariable Long userId) {
		return sinistreService.getSinitreByUserId(userId);
	}

	@GetMapping("sinistres/{sinistreId}")
	public SinistreDto getSinistreById(@PathVariable Long sinistreId) {
		return sinistreService.getSinistreById(sinistreId);
	}

	@GetMapping("sinistres/car/{sinistreId}")
	public CarDocument getCarSinistreById(@PathVariable Long sinistreId) {
		return carService.getBySinistreId(sinistreId).get(0);
	}

	@GetMapping("sinistres/health/{sinistreId}")
	public HealthDocument getHealthSinistreById(@PathVariable Long sinistreId) {
		return healthService.getBySinistreId(sinistreId).get(0);
	}

	@DeleteMapping("sinistres/{sinistreId}")
	public void deleteSinistreById(@PathVariable Long sinistreId) {
		sinistreService.delete(sinistreId);
	}

	@PostMapping("sinistres/health/save")
	public ResponseEntity<String> saveHealthSinistre(@RequestBody SinistreHealthDto sinistreHealthDto) {
		try {
			Sinistre sinistre = this.sinistreService.saveSinistre(new SinistreDto(sinistreHealthDto.getContractId(),
					sinistreHealthDto.getUserId(), sinistreHealthDto.getCategory(), sinistreHealthDto.getDate()));
			this.healthService.saveHealth(new HealthDocument(sinistre.getId(), sinistre.getContractId(),
					sinistre.getCategory(), sinistre.getDate(), sinistreHealthDto.getNature(),
					sinistreHealthDto.getDescription(), sinistreHealthDto.getHospital()));
			return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(sinistre.getId()));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("sinistres/car/save")
	public ResponseEntity<String> saveCarSinistre(@RequestBody SinistreCarDto sinistreCarDto) {
		try {
			Sinistre sinistre = this.sinistreService.saveSinistre(new SinistreDto(sinistreCarDto.getContractId(),
					sinistreCarDto.getUserId(), sinistreCarDto.getCategory(), sinistreCarDto.getDate()));
			this.carService.saveCar(new CarDocument(sinistre.getId(), sinistre.getContractId(), sinistre.getCategory(),
					sinistre.getDate(), sinistreCarDto.getImmatriculation(), sinistreCarDto.getBrand(),
					sinistreCarDto.getModele(), sinistreCarDto.getDescription(), sinistreCarDto.getResponsable()));
			return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(sinistre.getId()));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/sinistres/file/{sinistreId}")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
			@PathVariable Long sinistreId) {
		try {
			String filename = file.getOriginalFilename();
			String contentType = file.getContentType();
			long size = file.getSize();
			byte[] fileData = file.getBytes();

			FileDocument fileDocument = new FileDocument(filename, contentType, size, fileData, sinistreId);
			this.fileService.saveFile(fileDocument);
			return ResponseEntity.status(HttpStatus.CREATED).body("Fichier reçu avec succès.");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/sinistres/file/{sinistreId}")
	public List<FileDocument> getFileBySinistreId(@PathVariable Long sinistreId) {
		return this.fileService.findBySinistreId(sinistreId);
	}

}

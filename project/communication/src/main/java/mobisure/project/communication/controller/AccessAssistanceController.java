package mobisure.project.communication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mobisure.project.communication.entity.AccessAssistance;
import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.service.AccessAssistanceService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/access")
public class AccessAssistanceController {

	@Autowired
	private AccessAssistanceService service;
	
	 @GetMapping("/myFolder")
	 public List<Assistance> myFolder(@RequestParam Long idUser) {
		 return service.getMyFolder(idUser);
	 }
	 
	 @PostMapping("/addAccess")
	 public void addAccess(@RequestParam String idAssistance,@RequestParam Long idUser) {
		 service.addAccess(idAssistance, idUser);
	 }
	 
	 @GetMapping("/all")
	 public List<AccessAssistance> test(){
		 return service.getAll();
	 }
	
}

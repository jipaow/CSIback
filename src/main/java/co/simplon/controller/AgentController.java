package co.simplon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.model.Agent;
import co.simplon.model.Suspect;
import co.simplon.model.SuspectEnquete;
import co.simplon.service.AgentService;
import co.simplon.service.SuspectService;


@RestController
@RequestMapping("/csi")
public class AgentController {

	
	@Autowired
	 private AgentService agentService;
	 
		@RequestMapping(value = "/agents", method = RequestMethod.GET)
		public ResponseEntity <?> getAllAgent(){
			List <Agent> listAgent = null;
			try {
				listAgent= agentService.getAllAgent();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(listAgent);
		}
		
		
		@RequestMapping(value = "/agent/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getAgent(@PathVariable int id){
			Agent agent = null;
					
			try {
				agent = agentService.getAgent(id);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			if(agent == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
			return ResponseEntity.status(HttpStatus.OK).body(agent);
		}
		
		@RequestMapping(value ="/agent", method = RequestMethod.POST)
		public ResponseEntity<?> insertAgent(@RequestBody Agent agent){
			Agent resultAgent = null;
			String nom = agent.getNom();
			if((nom == null) || (nom.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom n'est pas saisi !");
			
			String prenom = agent.getPrenom();
			if((prenom == null) || (prenom.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prenom n'est pas saisi !");

			
			try {
				resultAgent = agentService.insertAgent(agent);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(resultAgent);
			
		}
		
		@RequestMapping(value ="/agent/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateAgent(@RequestBody Agent agent,@PathVariable int id) throws Exception{
			
			Agent result = null;
			
			String nom = agent.getNom();
		    if((nom == null) || (nom.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom n'est pas saisi !");
			
			String prenom = agent.getPrenom();
			if((prenom == null) || (prenom.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prenom n'est pas saisi !");
			
			try {
				result = agentService.updateAgent(id,agent);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			
			return  ResponseEntity.status(HttpStatus.CREATED).body(result);
			
		}
		
//		@RequestMapping(value = "/agent/link", method = RequestMethod.POST)
//		public ResponseEntity<?> addSuspectToEnquete(@RequestBody SuspectEnquete suspectEnquete){
//			SuspectEnquete resultSuspect = null;
//			
//			try {
//				resultSuspect = suspectService.addSuspectToEnquete(suspectEnquete);
//				System.out.println(resultSuspect);
//				
//			} catch (Exception e) {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//			}
//			System.out.println(resultSuspect);
//			return ResponseEntity.status(HttpStatus.CREATED).body(resultSuspect);
//			
//		}
}

package co.simplon.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.model.Suspect;
import co.simplon.model.SuspectEnquete;
import co.simplon.service.SuspectService;


@RestController
@RequestMapping("/csi")
public class SuspectController {
	
	 @Autowired
	 private SuspectService suspectService;
	 
		@RequestMapping(value = "/suspects", method = RequestMethod.GET)
		public ResponseEntity <?> getAllSuspect(){
			List <Suspect> listSuspect = null;
			try {
				listSuspect= suspectService.getAllSuspect();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(listSuspect);
		}
		
		
		@RequestMapping(value = "/suspect/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getSuspect(@PathVariable int id){
			Suspect suspect = null;
					
			try {
				suspect =suspectService.getSuspect(id);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			if(suspect == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
			return ResponseEntity.status(HttpStatus.OK).body(suspect);
		}
		
		@RequestMapping(value ="/suspect", method = RequestMethod.POST)
		public ResponseEntity<?> insertSuspect(@RequestBody Suspect suspect){
			Suspect resultSuspect = null;
			String nom = suspect.getNom();
			if((nom == null) || (nom.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom n'est pas saisi !");
			
			String prenom = suspect.getPrenom();
			if((prenom == null) || (prenom.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prenom n'est pas saisi !");
			
			String genre =suspect.getGenre();
			if((genre == null) || (genre.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez le sexe !");
			
			String adresseConnue = suspect.getAdresseConnues();
			if((adresseConnue == null) || (adresseConnue.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez votre chance de rentrer une adresse !");
			
			try {
				resultSuspect = suspectService.insertSuspect(suspect);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(resultSuspect);
			
		}
		
		@RequestMapping(value ="/suspect/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateSuspect(@RequestBody Suspect suspect,@PathVariable int id) throws Exception{
			
			Suspect result = null;
			
			String nom = suspect.getNom();
		    if((nom == null) || (nom.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom n'est pas saisi !");
			
			String prenom = suspect.getPrenom();
			if((prenom == null) || (prenom.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prenom n'est pas saisi !");
			
			String genre =suspect.getGenre();
			if((genre == null) || (genre.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez le sexe du suspect !");
			
			String adresseConnue = suspect.getAdresseConnues();
			if((adresseConnue == null) || (adresseConnue.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez votre chance de rentrer une adresse !");
			
			try {
				result = suspectService.updateSuspect(id,suspect);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			
			return  ResponseEntity.status(HttpStatus.CREATED).body(result);
			
		}
		
		@RequestMapping(value = "/suspect/link", method = RequestMethod.POST)
		public ResponseEntity<?> addSuspectToEnquete(@RequestBody SuspectEnquete suspectEnquete){
			SuspectEnquete resultSuspect = null;
			
			try {
				resultSuspect = suspectService.addSuspectToEnquete(suspectEnquete);
				System.out.println(resultSuspect);
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			System.out.println(resultSuspect);
			return ResponseEntity.status(HttpStatus.CREATED).body(resultSuspect);
			
		}
	



}

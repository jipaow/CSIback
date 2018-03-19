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

import co.simplon.model.Arme;
import co.simplon.model.Suspect;
import co.simplon.model.SuspectEnquete;
import co.simplon.service.ArmeService;
import co.simplon.service.SuspectService;


@RestController
@RequestMapping("/csi")
public class ArmeController {
	
	 @Autowired
	 private ArmeService armeService;
	 
		@RequestMapping(value = "/armes", method = RequestMethod.GET)
		public ResponseEntity <?> getAllArme(){
			List <Arme> listArme = null;
			try {
				listArme = armeService.getAllArme();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(listArme);
		}
		
		
		@RequestMapping(value = "/arme/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getArme(@PathVariable int id){
			Arme arme = null;
					
			try {
				arme =armeService.getArme(id);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			if(arme == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
			return ResponseEntity.status(HttpStatus.OK).body(arme);
		}
		
		@RequestMapping(value ="/arme", method = RequestMethod.POST)
		public ResponseEntity<?> insertArme(@RequestBody Arme arme){
			Arme resultArme = null;
			String type = arme.getType();
			if((type == null) || (type.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le type n'est pas saisi !");
			
			
			try {
				resultArme = armeService.insertArme(arme);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(resultArme);
			
		}
		
		@RequestMapping(value ="/arme/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateSuspect(@RequestBody Arme arme,@PathVariable int id) throws Exception{
			
			Arme result = null;
			
			String type = arme.getType();
		    if((type == null) || (type.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le type n'est pas saisi !");
			
			try {
				result = armeService.updateArme(id,arme);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			
			return  ResponseEntity.status(HttpStatus.CREATED).body(result);
			
		}
		// Méthode pour lier une arme à une enquête
//		@RequestMapping(value = "/arme/link", method = RequestMethod.POST)
//		public ResponseEntity<?> addSuspectToEnquete(@RequestBody SuspectEnquete suspectEnquete){
//			SuspectEnquete resultSuspect = null;
//			
//			try {
//				resultSuspect = armeService.addSuspectToEnquete(suspectEnquete);
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

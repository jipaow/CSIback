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

import co.simplon.model.Vehicule;
import co.simplon.service.VehiculeService;

@RestController
@RequestMapping("/csi")
public class VehiculeController {

	 @Autowired
	 private VehiculeService vehiculeService;
	 
		@RequestMapping(value = "/vehicules", method = RequestMethod.GET)
		public ResponseEntity <?> getAllVehicule(){
			List <Vehicule> listVehicule = null;
			try {
				listVehicule= vehiculeService.getAllVehicule();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(listVehicule);
		}
		
		
		@RequestMapping(value = "/vehicule/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getVehicule(@PathVariable int id){
			Vehicule vehicule = null;
					
			try {
				vehicule =vehiculeService.getVehicule(id);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			if(vehicule == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
			return ResponseEntity.status(HttpStatus.OK).body(vehicule);
		}
		
		@RequestMapping(value ="/vehicule", method = RequestMethod.POST)
		public ResponseEntity<?> insertVehicule(@RequestBody Vehicule vehicule){
			Vehicule resultVehicule = null;
			String marque = vehicule.getMarque();
			if((marque == null) || (marque.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La marque n'est pas saisi !");
			
			String modele = vehicule.getModele();
			if((modele == null) || (modele.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le modèle n'est pas saisi !");
			
			String immatriculation =vehicule.getImmatriculation();
			if((immatriculation == null) || (immatriculation.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez l'immatriculation !");
			
			
			try {
				resultVehicule = vehiculeService.insertVehicule(vehicule);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(resultVehicule);
			
		}
		
		@RequestMapping(value ="/vehicule/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateVehicule(@RequestBody Vehicule vehicule,@PathVariable int id) throws Exception{
			
			Vehicule result = null;
			
			String marque = vehicule.getMarque();
			if((marque == null) || (marque.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La marque n'est pas saisi !");
			
			String modele = vehicule.getModele();
			if((modele == null) || (modele.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le modèle n'est pas saisi !");
			
			String immatriculation =vehicule.getImmatriculation();
			if((immatriculation == null) || (immatriculation.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez l'immatriculation !");
			
			try {
				result = vehiculeService.updateVehicule(id,vehicule);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			
			return  ResponseEntity.status(HttpStatus.CREATED).body(result);
			
		}
//TODO méthode de lien d'un véhicule à une affaire		
//		@RequestMapping(value = "/vehicule/link", method = RequestMethod.POST)
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

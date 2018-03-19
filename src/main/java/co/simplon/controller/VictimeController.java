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

import co.simplon.model.Victime;
import co.simplon.service.VictimeService;

@RestController
@RequestMapping("/csi")
public class VictimeController {

		
		 @Autowired
		 private VictimeService victimeService;
		 
			@RequestMapping(value = "/victimes", method = RequestMethod.GET)
			public ResponseEntity <?> getAllVictime(){
				List <Victime> listVictime = null;
				try {
					listVictime= victimeService.getAllVictime();
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
				}
				
				return ResponseEntity.status(HttpStatus.OK).body(listVictime);
			}
			
			
			@RequestMapping(value = "/victime/{id}", method = RequestMethod.GET)
			public ResponseEntity<?> getVictime(@PathVariable int id){
				Victime victime = null;
						
				try {
					victime = victimeService.getVictime(id);
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
				}
				
				if(victime == null)
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
				
				return ResponseEntity.status(HttpStatus.OK).body(victime);
			}
			
			@RequestMapping(value ="/victime", method = RequestMethod.POST)
			public ResponseEntity<?> insertVictime(@RequestBody Victime victime){
				Victime resultVictime = null;
				String nom = victime.getNom();
				if((nom == null) || (nom.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom n'est pas saisi !");
				
				String prenom = victime.getPrenom();
				if((prenom == null) || (prenom.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prenom n'est pas saisi !");
				
				String genre = victime.getGenre();
				if((genre == null) || (genre.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez le sexe !");
				
				String adresse = victime.getAdresse();
				if((adresse == null) || (adresse.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez votre chance de rentrer une adresse !");
				
				try {
					resultVictime = victimeService.insertVictime(victime);
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
				}
				
				return ResponseEntity.status(HttpStatus.CREATED).body(resultVictime);
				
			}
			
			@RequestMapping(value ="/victime/{id}", method = RequestMethod.PUT)
			public ResponseEntity<?> updateVictime(@RequestBody Victime victime,@PathVariable int id) throws Exception{
				
				Victime result = null;
				
				String nom = victime.getNom();
			    if((nom == null) || (nom.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom n'est pas saisi !");
				
				String prenom = victime.getPrenom();
				if((prenom == null) || (prenom.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prenom n'est pas saisi !");
				
				String genre = victime.getGenre();
				if((genre == null) || (genre.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez le sexe du suspect !");
				
				String adresse =  victime.getAdresse();
				if((adresse == null) || (adresse.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez votre chance de rentrer une adresse !");
				
				try {
					result = victimeService.updateVictime(id,victime);
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
				}
				
				return  ResponseEntity.status(HttpStatus.CREATED).body(result);
				
			}
}

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

import co.simplon.model.Temoin;
import co.simplon.service.TemoinService;

@RestController
@RequestMapping("/csi")
public class TemoinController {

		
		 @Autowired
		 private TemoinService temoinService;
		 
			@RequestMapping(value = "/temoins", method = RequestMethod.GET)
			public ResponseEntity <?> getAllTemoin(){
				List <Temoin> listTemoin = null;
				try {
					listTemoin= temoinService.getAllTemoin();
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
				}
				
				return ResponseEntity.status(HttpStatus.OK).body(listTemoin);
			}
			
			
			@RequestMapping(value = "/temoin/{id}", method = RequestMethod.GET)
			public ResponseEntity<?> getTemoin(@PathVariable int id){
				Temoin temoin = null;
						
				try {
					temoin = temoinService.getTemoin(id);
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
				}
				
				if(temoin == null)
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
				
				return ResponseEntity.status(HttpStatus.OK).body(temoin);
			}
			
			@RequestMapping(value ="/temoin", method = RequestMethod.POST)
			public ResponseEntity<?> insertTemoin(@RequestBody Temoin temoin){
				Temoin resultTemoin = null;
				String nom = temoin.getNom();
				if((nom == null) || (nom.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom n'est pas saisi !");
				
				String prenom = temoin.getPrenom();
				if((prenom == null) || (prenom.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prenom n'est pas saisi !");
				
				String genre = temoin.getGenre();
				if((genre == null) || (genre.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez le sexe !");
				
				String adresse = temoin.getAdresse();
				if((adresse == null) || (adresse.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez votre chance de rentrer une adresse !");
				
				try {
					resultTemoin = temoinService.insertTemoin(temoin);
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
				}
				
				return ResponseEntity.status(HttpStatus.CREATED).body(resultTemoin);
				
			}
			
			@RequestMapping(value ="/temoin/{id}", method = RequestMethod.PUT)
			public ResponseEntity<?> updateTemoin(@RequestBody Temoin temoin,@PathVariable int id) throws Exception{
				
				Temoin result = null;
				
				String nom = temoin.getNom();
			    if((nom == null) || (nom.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom n'est pas saisi !");
				
				String prenom = temoin.getPrenom();
				if((prenom == null) || (prenom.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prenom n'est pas saisi !");
				
				String genre = temoin.getGenre();
				if((genre == null) || (genre.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez le sexe du suspect !");
				
				String adresse =  temoin.getAdresse();
				if((adresse == null) || (adresse.isEmpty()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("saisissez votre chance de rentrer une adresse !");
				
				try {
					result = temoinService.updateTemoin(id,temoin);
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
				}
				
				return  ResponseEntity.status(HttpStatus.CREATED).body(result);
				
			}
}

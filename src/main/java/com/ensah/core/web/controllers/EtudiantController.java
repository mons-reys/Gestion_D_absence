package com.ensah.core.web.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensah.core.bo.Absence;
import com.ensah.core.bo.CadreAdministrateur;
import com.ensah.core.bo.Compte;
import com.ensah.core.bo.Enseignant;
import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.Inscription;
import com.ensah.core.bo.Message;
import com.ensah.core.bo.TypeSeance;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.services.IEtudiantService;
import com.ensah.core.services.IInscriptionService;
import com.ensah.core.services.IPersonService;
import com.ensah.core.services.ITypeSeanceService;
import com.ensah.core.utils.User;
import com.ensah.core.web.models.AccountModel;
import com.ensah.core.web.models.MessageModel;
import com.ensah.core.web.models.PersonModel;
import com.ensah.core.web.models.UserAndAccountInfos;

@Controller
@RequestMapping("/student")
public class EtudiantController {
	
	//user inforamtion
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IInscriptionService inscriptionService;
	
	@Autowired
	private IEtudiantService etudiantService;
	
	@Autowired
	private ITypeSeanceService typeSeanceService;
	
	
	
	
	//send date to the service to update it 
	@RequestMapping("updatePerson")
	public String updatePerson(@Valid @ModelAttribute("personModel") PersonModel person, BindingResult bindingResult,
			Model model) {

		// En cas d'erreur
		if (bindingResult.hasErrors()) {

			return "student/updateForm";
		}

		// On copie les données du modèle vers l'objet métier puis on appel le service
		// pour faire la mise à jour
		if (person.getTypePerson() == PersonModel.TYPE_STUDENT) {
			Etudiant etd = new Etudiant();
			BeanUtils.copyProperties(person, etd);
			personService.updatePerson(etd);

		}else {
			System.out.print("error");
		}

		// Mettre le message de succès dans le modèle
		model.addAttribute("msg", "Opération effectuée avec succès");

		return "student/updateForm";
	}
	
	
	
	//update data in the view
	@RequestMapping(value = "updatePersonForm/{idPerson}", method = RequestMethod.GET)
	public String updatePersonForm(@PathVariable int idPerson, Model model) {

		// On reoit comme paramètre l'id de la personne à mettre à jour
		Utilisateur utl = personService.getPersonById(Long.valueOf(idPerson));

		// On construit le modèle
		PersonModel pm = new PersonModel();

		// En fonction due type de l'utilisateur à modifier
		// Ceci va nous pemettre d'afficher un formulaire adapté
		// slon le type de la personne
		if (utl instanceof Etudiant) {
			BeanUtils.copyProperties((Etudiant) utl, pm);
			pm.setTypePerson(PersonModel.TYPE_STUDENT);
		} else {
			System.out.print("no");
		}

		// Initialiser le modele avec la personne
		model.addAttribute("personModel", pm);

		return "student/updateForm";
	}
	
	
	
	
	
	
	//filter of inscriptions by annee
	public Inscription filterInscriptionsByAnnee(Long annee, List<Inscription> inscriptions) {
		//get inscriptionByannee
		for(Inscription ins: inscriptions) {
			if(ins.getAnnee() == 2021) return ins;
		}
		return null;
	}
		
	//get absence
	//update data in the view
		@RequestMapping(value = "getAbsence/{idPerson}", method = RequestMethod.GET)
		public String getAbsence1(@PathVariable int idPerson, Model model) {
			
			//charger l'inscription par idEtudiant + annee
			 Inscription currentInscription = inscriptionService.getInscriptionByIdEtudiantEtAnnee(String.valueOf(idPerson), 2020);
			 
			//get absence par cette inscription
			Set<Absence> abs =currentInscription.getAbsences();
			
			
			
			/*for(Absence ab: abs) {
				AT.put(ab, ab.getTypeSeance());
				ab.getObservateur();
			}*/
			
			
			//passer au view
			model.addAttribute("absenceModel", abs);
			
			
			
			//get all inscriptions
			 List<Inscription> inscriptions = inscriptionService.getInscriptionByIdEtudiant(String.valueOf(idPerson));
			
			model.addAttribute("currentInscriptionModel", currentInscription);
			
			model.addAttribute("inscriptionsModel", inscriptions);
			
			
			//charger les donnee de l'utilisateur connecter
			User user = new User(this.httpSession);
			UserAndAccountInfos infoUser = user.getUserAccount();
			model.addAttribute("userInfo", infoUser);
			
			
			/*model.addAttribute("typeSeanceModel", AT);*/
			

			return "student/myAbsence";
		}
		
		
		
		//get absence
		//update data in the view
			@RequestMapping(value = "getAbsence/{idPerson}/{annee}", method = RequestMethod.GET)
			public String getAbsence2(@PathVariable int idPerson, @PathVariable int annee, Model model) {
				
				//charger l'inscription par idEtudiant + annee
				 Inscription currentInscription = inscriptionService.getInscriptionByIdEtudiantEtAnnee(String.valueOf(idPerson),Integer.valueOf(annee));
				 
				//get absence par cette inscription
				Set<Absence> abs = currentInscription.getAbsences();
				
				
				//passer au view
				model.addAttribute("absenceModel", abs);
				
				
				//--pour charger la list des choix des annees d'absence---
				//get all inscriptions
				 List<Inscription> inscriptions = inscriptionService.getInscriptionByIdEtudiant(String.valueOf(idPerson));
				
				model.addAttribute("currentInscriptionModel", currentInscription);
				
				model.addAttribute("inscriptionsModel", inscriptions);
				
				
				//charger les donnee de l'utilisateur connecter pour reutiliser dans les liens des annees
				User user = new User(this.httpSession);
				UserAndAccountInfos infoUser = user.getUserAccount();
				model.addAttribute("userInfo", infoUser);
					
				return "student/myAbsence";
			}
	
			
			
			@RequestMapping(value = "reclamation/{idPerson}/{idAbsence}", method = RequestMethod.GET)
			public String reclamationHandler(@PathVariable int idPerson, @PathVariable int idAbsence, Model model) {
				model.addAttribute("messageModel", new MessageModel());
				return "student/reclamation";
			}
			
			
			
			 
			@RequestMapping("addReclamation/{idPerson}/{idAbsence}")
			 public String addReclamation( @PathVariable int idPerson, @PathVariable int idAbsence , @Valid @ModelAttribute("messageModel") MessageModel reclamation,
				      Model model,  BindingResult result) {
				        if (result.hasErrors()) {
				            return "error";
				        }
				        
				        //save the reclamation as message 
				        System.out.println(reclamation.getTexte());
				        
				    
				        Message msg = new Message();
				        //pass data from model to Message class
				        msg.setTexte(reclamation.getTexte());
				        //get expiditeur by id 
				        
				        
						
						System.out.println(msg.getTexte());
							
				        model.addAttribute("messageModel", new MessageModel());

				        return "student/reclamation";
				    }

	
		
			

	

	
}

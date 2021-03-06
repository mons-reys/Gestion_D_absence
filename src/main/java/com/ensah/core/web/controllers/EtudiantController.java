package com.ensah.core.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensah.core.bo.Absence;
import com.ensah.core.bo.Compte;
import com.ensah.core.bo.Conversation;
import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.Inscription;
import com.ensah.core.bo.Message;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.services.ICompteService;
import com.ensah.core.services.IConversationService;
import com.ensah.core.services.IInscriptionService;
import com.ensah.core.services.IMessageService;
import com.ensah.core.services.IPersonService;
import com.ensah.core.utils.User;
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
	private ICompteService compteService;
	
	
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private IConversationService conversationService;
	
	
	
	
	
	//send date to the service to update it 
	@RequestMapping("updatePerson")
	public String updatePerson(@Valid @ModelAttribute("personModel") PersonModel person, BindingResult bindingResult,
			Model model) {

		// En cas d'erreur
		if (bindingResult.hasErrors()) {
			return "student/updateForm";
		}

		// On copie les donn??es du mod??le vers l'objet m??tier puis on appel le service
		// pour faire la mise ?? jour
		if (person.getTypePerson() == PersonModel.TYPE_STUDENT) {
			Etudiant etd = new Etudiant();
			BeanUtils.copyProperties(person, etd);
			personService.updatePerson(etd);

		}else {
			System.out.print("error");
		}

		// Mettre le message de succ??s dans le mod??le
		model.addAttribute("msg", "Op??ration effectu??e avec succ??s");

		return "student/updateForm";
	}
	
	
	
	//update data in the view
	@RequestMapping(value = "updatePersonForm/{idPerson}", method = RequestMethod.GET)
	public String updatePersonForm(@PathVariable int idPerson, Model model) {

		// On reoit comme param??tre l'id de la personne ?? mettre ?? jour
		Utilisateur utl = personService.getPersonById(Long.valueOf(idPerson));

		// On construit le mod??le
		PersonModel pm = new PersonModel();

		// En fonction due type de l'utilisateur ?? modifier
		// Ceci va nous pemettre d'afficher un formulaire adapt??
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
			
			
			//passer au view
			model.addAttribute("absenceModel", abs);
			
			//----try to get the teacher 
			List<Compte> comptes = null;
			for(Absence ab : abs) {
				Set<Compte> c = ab.getObservateur().getComptes();
				for(Compte s: c) {
					Compte temp = compteService.getAccountByIdRole((long) 2);
					comptes.add(temp);
					System.out.println("id de comepte: " + temp.getIdCompte());
				}
			}
			model.addAttribute("profModel", comptes);	
			//-----------------------------
			
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
	
			
			
			@RequestMapping(value = "reclamation/{idPerson}/{idEnseignant}", method = RequestMethod.GET)
			public String reclamationHandler(@PathVariable int idPerson, @PathVariable int idEnseignant, Model model) {
				
				//get compte
				//Compte compte = compteService.getAccountById((long) idPerson);
				//List<Conversation> conv = compte.getConversationsCrees();

				//Compte creator = compteService.getAccountByIdUtilisateur((long) idPerson);
				Compte creator = compteService.getAccountById((long) idPerson);

				//show the conversation
				//get conversationBy IdExp And IdDest conversationService.GetConversationByIdExpAndIdDest((long) idPerson, (long) idEnseignant);
				 //check if the conversation null to create new one 
		        
				Conversation conv = conversationService.getConversationByIdCreatorAndType(idPerson, "reclamation");
				System.out.println("titre : "  + conv.getTitre());
				
				if(conv == null) {
					 conv = new Conversation();
		        	//set values for the conversation
		        	conv.setEtat(0);
		        	conv.setTitre("reclamation d'absence");
		        	conv.setType("reclamation");
		        	conv.setCreateurConversation(creator);
		        	//save the conversation
		        	conversationService.addConversation(conv);
				}
		        	//System.out.println(conv.toString());
					model.addAttribute("conversationModel", conv);

			
		        
				
				
				
				MessageModel messageModel = new MessageModel();
				messageModel.setIdConversation(conv.getIdConversation());
				messageModel.setExpediteurId((long) idPerson);
				
				
				model.addAttribute("messageModel", messageModel);
				model.addAttribute("idPersonModel", idPerson);
				model.addAttribute("idEnseignantModel", idEnseignant);
				
				return "student/reclamation";
			}
			
			
			 
			@RequestMapping("addReclamation")
			 public String addReclamation(@Valid @ModelAttribute("messageModel") MessageModel reclamation,
				      Model model,  BindingResult result) {
				
						//error case 
				        if (result.hasErrors()) {
				        	return "student/reclamation";
				        }
				        
				        // test
				        //System.out.println(reclamation.getTexte());
				        //System.out.println(reclamation.getExpediteurId());
				        
				        
				        
				        
				        //get expediteur & destinataire account 
				        Compte expediteur = compteService.getAccountById(Long.valueOf(reclamation.getExpediteurId()));
				        Compte destinataire = compteService.getAccountByLogin("cadre_admin");
				        
				       
				        //create a conversation
						//Conversation conversation = conversationService.GetConversationByIdExpAndIdDest((long) reclamation.getExpediteurId(), (long) reclamation.getDestinataireId());

				        Conversation conversation = conversationService.getConversationById(reclamation.getIdConversation());
				        System.out.println("le id: " + reclamation.getIdConversation());
				        System.out.println("passed");
				        
				        
				      
					        
					        
				        	conversation.setCreateurConversation(expediteur);
					      //add participant to the list 
					        List<Compte> participants = new ArrayList<Compte>();
					        participants.add(expediteur);
					        participants.add(destinataire);
					        conversation.setParticipant(participants);
				        
				       
				        //create a new message 
				        Message msg = new Message();
				        
				        //pass data from model to Message class
				        msg.setTexte(reclamation.getTexte());
				        msg.setExpediteur(expediteur);
				        msg.setDestinataire(destinataire);
				        msg.setDateHeure(new Date());
				        msg.setConversation(conversation);
				      
				        //set the message to the conversation
				        List<Message> messages = new ArrayList<Message>();
				        messages.add(msg);
				        conversation.setMessages(messages);
				        
				        //save the record
				        conversationService.addConversation(conversation);
				        messageService.addMessage(msg);
				        
				     

						System.out.println(msg.toString());
							
				     // Mettre le message de succ??s dans le mod??le
						model.addAttribute("msg", "Operation effectuee avec succes");

				        return  "redirect:/student/reclamation/" + reclamation.getExpediteurId()+"/" +reclamation.getDestinataireId() ;
				    }

	
		
			

	

	
}

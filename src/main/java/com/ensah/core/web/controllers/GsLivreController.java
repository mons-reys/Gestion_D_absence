package com.ensah.core.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensah.core.bo.Emprunt;
import com.ensah.core.services.IGsLivresServices;
import com.ensah.genericdao.EntityNotFoundException;

@Controller
@RequestMapping("/biblio")
public class GsLivreController {


	@Autowired
	private IGsLivresServices gsLivreServices;

	@RequestMapping(value = "empruntForm", method = RequestMethod.GET)
	public String empruntForm( Model model) {

		Emprunt emp = new Emprunt();
		model.addAttribute("empruntModel", emp);

		return "biblio/empruntForm";
	}

	@PostMapping("/realiserEmprunt")
	public String realiserEmprunt(@ModelAttribute("empruntModel") Emprunt emprunt, Model model) {
		try {
			gsLivreServices.addEmprunt(emprunt.getLivre().getCodeLivre(), emprunt.getUtilisateur().getCin());
			// Enregistrer un message de succes
			model.addAttribute("msg", "Emprunt enregistrée avec succès");
			return "biblio/empruntForm";
		} catch (EntityNotFoundException e) {
			// Enregistrer un message d'erreur
			model.addAttribute("error", e.getMessage());
		}
		return "biblio/empruntForm";
	}

}

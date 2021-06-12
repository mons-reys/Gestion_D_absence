package com.ensah.core.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ensah.core.bo.Compte;
import com.ensah.core.bo.UserPrincipal;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.web.models.UserAndAccountInfos;

public class User {
	@Autowired
	private HttpSession httpSession;

	public User(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	/**
	 * Récupère les données de l'utilisateur connecté du contexte de securité et le
	 * stocke dans un objet personnalisé à enregistrer dans la session http
	 * 
	 * @return
	 */
	public UserAndAccountInfos getUserAccount() {

		// On vérifie si les infors de l'utilisateur sont déjà dans la session
		UserAndAccountInfos userInfo = (UserAndAccountInfos) httpSession.getAttribute("userInfo");

		if (userInfo == null) {

			// On obtient l'objet representant le compte connecté (Objet UserPrincipal
			// implémentant UserDetails)
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			// On cast vers notre objet UserPrincipal
			Compte userAccount = ((UserPrincipal) principal).getUser();

			Utilisateur u = userAccount.getProprietaire();

			userInfo = new UserAndAccountInfos(u.getIdUtilisateur(), userAccount.getIdCompte(), userAccount.getLogin(),
					u.getNom(), u.getPrenom(), u.getEmail());

			// On le stocke dans la session
			httpSession.setAttribute("userInfo", userInfo);
		}

		return userInfo;

	}

}

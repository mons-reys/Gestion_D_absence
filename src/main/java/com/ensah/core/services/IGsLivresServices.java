package com.ensah.core.services;

import com.ensah.core.bo.Livre;

public interface IGsLivresServices {

	Livre getLivreById(Long idLivre);
	void addEmprunt(String codeLivre, String cin);
	Livre getLivreByCode(String codeLivre);

}

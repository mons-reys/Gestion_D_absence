package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.Inscription;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.utils.ExcelExporter;

public interface IEtudiantService {

	public void addPerson(Utilisateur pPerson);

	public void updatePerson(Utilisateur pPerson);

	public List<Inscription> getAllInscription();
	
	public void deletePerson(Long id);

	public Etudiant getEtudiantById(Long id);
	
	public Utilisateur getPersonByCin(String cin);
	
	public ExcelExporter preparePersonExport(List<Utilisateur> persons);
	
	

}

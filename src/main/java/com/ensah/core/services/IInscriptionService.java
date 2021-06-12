package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Inscription;
import com.ensah.core.utils.ExcelExporter;

public interface IInscriptionService {

	public void addInscription(Inscription pInscription);


	public List<Inscription> getAllInscriptions();

	public void deleteInscription(Long id);

	public Inscription getInscriptionById(Long id);
	
	public Inscription getInscriptionByAnnee(long annee);
	
	public ExcelExporter prepareInscriptionExport(List<Inscription> Inscriptions);
	
	public Inscription getInscriptionByIdEtudiant(String idEtudiant);
	
	

}

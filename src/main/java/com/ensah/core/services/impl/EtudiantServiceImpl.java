package com.ensah.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.Inscription;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.dao.IEtudiantDao;
import com.ensah.core.dao.IInscriptionDao;
import com.ensah.core.dao.IUtilisateurDao;
import com.ensah.core.services.IEtudiantService;
import com.ensah.core.services.IPersonService;
import com.ensah.core.utils.ExcelExporter;

@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiantService {

	@Autowired
	private IInscriptionDao inscriptionDao;
	
	@Autowired
	private IEtudiantDao etudiantDao;

	
	public List<Inscription> getAllInscription() {

		return inscriptionDao.getAll();
	}




	@Override
	public void addPerson(Utilisateur pPerson) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void updatePerson(Utilisateur pPerson) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void deletePerson(Long id) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public Etudiant getEtudiantById(Long id) {
		return etudiantDao.findById(id);
		
	};
	




	@Override
	public Utilisateur getPersonByCin(String cin) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public ExcelExporter preparePersonExport(List<Utilisateur> persons) {
		// TODO Auto-generated method stub
		return null;
	}




}
 
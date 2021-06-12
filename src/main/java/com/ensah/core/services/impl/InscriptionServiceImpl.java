package com.ensah.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Inscription;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.dao.IInscriptionDao;
import com.ensah.core.services.IInscriptionService;
import com.ensah.core.services.IInscriptionService;
import com.ensah.core.utils.ExcelExporter;

@Service
@Transactional
public class InscriptionServiceImpl implements IInscriptionService {

	@Autowired
	private IInscriptionDao inscriptionDao;

	
	public List<Inscription> getInscriptionByIdEtudiant(String idEtudiant) {
		List<Inscription> u = inscriptionDao.getEntityByColValue("Inscription", "idEtudiant", idEtudiant);
		if (u != null && u.size() != 0) {
			return inscriptionDao.getEntityByColValue("Inscription", "idEtudiant", idEtudiant);
		}
		
		return null;
	}

	

	public Inscription getInscriptionByAnnee(String annee) {
		List<Inscription> u = inscriptionDao.getEntityByColValue("Inscription", "annee", annee);
		if (u != null && u.size() != 0) {
			return inscriptionDao.getEntityByColValue("Inscription", "annee", annee).get(0);
		}

		return null;

	}

	

	/*public ExcelExporter prepareInscriptionExport(List<Inscription> Inscriptions) {
		String[] columnNames = new String[] { "Nom", "Prénom", "CIN", "Email", "Télé" };
		String[][] data = new String[Inscriptions.size()][5];

		int i = 0;
		for (Inscription u : Inscriptions) {
			data[i][0] = u.getNom();
			data[i][1] = u.getPrenom();
			data[i][2] = u.getCin();
			data[i][3] = u.getEmail();
			data[i][4] = u.getTelephone();
			i++;
		}

		return new ExcelExporter(columnNames, data, "Inscriptions");

	}*/

	

	@Override
	public void addInscription(Inscription pInscription) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public List<Inscription> getAllInscriptions() {
		// TODO Auto-generated method stub
		return  inscriptionDao.getAll();
	}

	@Override
	public void deleteInscription(Long id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public ExcelExporter prepareInscriptionExport(List<Inscription> Inscriptions) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Inscription getInscriptionById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Inscription getInscriptionByAnnee(long annee) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Inscription getInscriptionByIdEtudiantEtAnnee(String idEtudiant, long annee) {
		//get by id etudiant
		List<Inscription> inscriptions = this.getInscriptionByIdEtudiant(idEtudiant);
		
		//filtere les resultat par annee
		for(Inscription ins: inscriptions) {
			if(ins.getAnnee() == annee) return ins;
		}
		return null;
	}

}

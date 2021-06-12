package com.ensah.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.Inscription;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.dao.IInscriptionDao;
import com.ensah.core.dao.IUtilisateurDao;
import com.ensah.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class InscriptionDaoImpl extends HibernateSpringGenericDaoImpl<Inscription, Long> implements IInscriptionDao {

	public InscriptionDaoImpl() {
		super(Inscription.class);
	}

}

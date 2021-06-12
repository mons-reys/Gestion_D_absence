package com.ensah.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.dao.IEtudiantDao;
import com.ensah.core.dao.IUtilisateurDao;
import com.ensah.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class EtudiantDaoImpl extends HibernateSpringGenericDaoImpl<Etudiant, Long> implements IEtudiantDao {

	public EtudiantDaoImpl() {
		super(Etudiant.class);
	}

}

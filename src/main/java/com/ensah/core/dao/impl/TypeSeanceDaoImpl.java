package com.ensah.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.TypeSeance;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.dao.ITypeSeanceDao;
import com.ensah.core.dao.IUtilisateurDao;
import com.ensah.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class TypeSeanceDaoImpl extends HibernateSpringGenericDaoImpl<TypeSeance, Long> implements ITypeSeanceDao {

	public TypeSeanceDaoImpl() {
		super(TypeSeance.class);
	}

}

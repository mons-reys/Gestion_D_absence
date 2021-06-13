package com.ensah.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Message;
import com.ensah.core.bo.TypeSeance;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.dao.IMessageDao;
import com.ensah.core.dao.ITypeSeanceDao;
import com.ensah.core.dao.IUtilisateurDao;
import com.ensah.core.services.IMessageService;
import com.ensah.core.services.IPersonService;
import com.ensah.core.services.ITypeSeanceService;
import com.ensah.core.utils.ExcelExporter;

@Service
@Transactional
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private IMessageDao messageDao;

	@Override
	public void addMessage(Message messsage) {
		messageDao.create(messsage);
	}

	

	

	

}

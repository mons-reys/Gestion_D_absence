package com.ensah.core.dao.impl;
import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Message;
import com.ensah.core.dao.IMessageDao;
import com.ensah.genericdao.HibernateSpringGenericDaoImpl;


@Repository
public class MessageDaoImpl extends HibernateSpringGenericDaoImpl<Message , Long> implements IMessageDao {

	public MessageDaoImpl() {
		super(Message.class);
	}

}

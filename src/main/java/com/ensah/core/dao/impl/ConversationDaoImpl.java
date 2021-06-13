package com.ensah.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Conversation;
import com.ensah.core.dao.IConversationDao;
import com.ensah.genericdao.HibernateSpringGenericDaoImpl;


@Repository
public class ConversationDaoImpl extends HibernateSpringGenericDaoImpl<Conversation ,Long> implements IConversationDao {

	public ConversationDaoImpl() {
		super(Conversation.class);
		// TODO Auto-generated constructor stub
	}

}

package com.ensah.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Conversation;
import com.ensah.core.bo.Message;
import com.ensah.core.dao.IConversationDao;
import com.ensah.core.services.IConversationService;


@Service
@Transactional
public class ConversationServiceImpl implements IConversationService {

	@Autowired
	private IConversationDao conversationDao;

	@Override
	public void addConversation(Conversation conversation) {
		conversationDao.create(conversation);
	}

	

	

	

	

}

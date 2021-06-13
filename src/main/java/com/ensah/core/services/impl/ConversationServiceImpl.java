package com.ensah.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Conversation;
import com.ensah.core.bo.Inscription;
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

	@Override
	public Conversation getConversationById(Long id) {
		
		return null;
	}
	
	
	


	
	@Override
	public List<Conversation> getConversationByIdCreateur(String idCreateur) {
		System.out.println(idCreateur);
		List<Conversation> u = conversationDao.getEntityByColValue("Conversation", "idCreateur",idCreateur);
		if (u != null && u.size() != 0) {
			return conversationDao.getEntityByColValue("Conversation", "idCreateur", idCreateur);
		}
		
		return null;
	}
	

	@Override
	public Conversation GetConversationByIdExpAndIdDest(Long idExp, Long idDest) {
		System.out.println(String.valueOf(idExp));
		//get the conversations created by the student
		List<Conversation> conversations = this.getConversationByIdCreateur(String.valueOf(idExp));
		
		
		//filter the conversations based on idExp and idDest
		for(Conversation cnv: conversations) {
			
			//get the conversation from the messages with the same ids;
			List<Message> messages = cnv.getMessages();
				for(Message msg: messages) {
					if(msg.getDestinataire().getIdCompte() == idDest) return cnv;
			}
		}
		
		return null;
		
	}

	

	

	

	

	

}

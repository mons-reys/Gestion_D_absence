package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Conversation;



public interface IConversationService {

	public void addConversation(Conversation conversation);
	
	public Conversation getConversationByType(String type);

	
	public Conversation getConversationById(Long id);
	
	public List<Conversation> getConversationByIdCreateur(String idCreateur);
	
	public Conversation GetConversationByIdExpAndIdDest(Long idExp, Long idDest);

	public Conversation getConversationByIdCreatorAndType(int idPerson, String string);

}

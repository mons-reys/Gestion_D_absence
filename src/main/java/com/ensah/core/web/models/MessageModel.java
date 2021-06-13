package com.ensah.core.web.models;

//Classe model utilisé dans la partie web
// pour recevoir les données de la vue 
//utilisée pour créer les comptes
//C'est une classe non persistante
public class MessageModel {
	
	private String texte;
	
	private Long messageId;
	
	private Long expediteurId;
	
	private Long destinataireId;
	
	
	
	public MessageModel() {
	}

	public MessageModel(String texte) {
		this.texte = texte;
	}

	public MessageModel(String text, Long messageId, Long expediteurId, Long destinataireId) {
		super();
		this.texte = text;
		this.messageId = messageId;
		this.expediteurId = expediteurId;
		this.destinataireId = destinataireId;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getExpediteurId() {
		return expediteurId;
	}

	public void setExpediteurId(Long expediteurId) {
		this.expediteurId = expediteurId;
	}

	public Long getDestinataireId() {
		return destinataireId;
	}

	public void setDestinataireId(Long destinataireId) {
		this.destinataireId = destinataireId;
	}

	
	
	
	
}

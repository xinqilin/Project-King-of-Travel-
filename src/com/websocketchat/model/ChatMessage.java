package com.websocketchat.model;

public class ChatMessage {
	// type: open, close, chat
	private String type;
	private String sender;
	private String receiver;
	private String content;
	// messageType: text, image
	private String messageType;

	public ChatMessage(String type, String sender, String receiver, String content, String messageType) {
		super();
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.messageType = messageType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
}

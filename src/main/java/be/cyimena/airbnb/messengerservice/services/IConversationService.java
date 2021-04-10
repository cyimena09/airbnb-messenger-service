package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.models.Conversation;

import java.util.List;

public interface IConversationService {

    List<Conversation> getConversationsByUserId();

    Conversation getConversationById();

}

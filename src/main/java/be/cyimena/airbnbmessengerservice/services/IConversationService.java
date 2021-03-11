package be.cyimena.airbnbmessengerservice.services;

import be.cyimena.airbnbmessengerservice.models.Conversation;

import java.util.List;

public interface IConversationService {

    List<Conversation> getConversationsByUserId();

    Conversation getConversationById();

}

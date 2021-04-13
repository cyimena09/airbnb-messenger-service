package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.models.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IConversationService {

    Conversation getConversationById(Integer id);

    Page<Conversation> getConversationsByParticipantId(Integer id, Pageable pageable);

    Conversation createConversation(Conversation conversation);

}

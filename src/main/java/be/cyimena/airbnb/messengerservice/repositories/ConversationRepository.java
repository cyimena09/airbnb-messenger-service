package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    Conversation findConversationById(Integer id);


}
